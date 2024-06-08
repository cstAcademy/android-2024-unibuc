package com.cst.cstacademyunibuc.fragments.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.adapters.CartItemListAdapter
import com.cst.cstacademyunibuc.data.repositories.products.ProductsRepository
import com.cst.cstacademyunibuc.data.repositories.products.ProductsRepositoryLocal
import com.cst.cstacademyunibuc.data.repositories.users.UserRepository
import com.cst.cstacademyunibuc.helpers.VolleyRequestQueue
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.managers.SharedPrefsManager
import com.cst.cstacademyunibuc.models.CartItemModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.api.toCartItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListFragmentViewModel @Inject constructor(
    private val productRepository: ProductsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val products = productRepository.getAllProducts().map { productsList ->
        adapter.updateList(productsList.toCartItems())
    }

    val adapter = CartItemListAdapter(listOf())

    val users = userRepository.getPlebUsers()
    val usersAdmin = userRepository.getAdminUsers()

    fun getCartItems() {
        val url = "${BuildConfig.BASE_URL}products"

        val stringRequest = object: StringRequest(
            Request.Method.GET,
            url,
            { response ->
                "success".logErrorMessage()
                handleProductsResponse(response)
            },
            {
                "That didn't work!".logErrorMessage()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> = HashMap()

                SharedPrefsManager.readToken()?.let { token ->
                    headers["Authorization"] = token
                }

                return headers
            }
        }

        VolleyRequestQueue.addToRequestQueue(stringRequest)
    }

    private fun handleProductsResponse(response: String) {
        val collectionType = object : TypeToken<List<ProductAPIModel>>() {}.type
        val responseJsonArray = Gson().fromJson<List<ProductAPIModel>>(response, collectionType)

        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertAllProducts(responseJsonArray)
        }
    }
}