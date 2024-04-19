package com.cst.cstacademyunibuc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.adapters.CartItemListAdapter
import com.cst.cstacademyunibuc.data.ProductsRepository
import com.cst.cstacademyunibuc.helpers.VolleyRequestQueue
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.models.CartItemModel
import com.cst.cstacademyunibuc.models.CategoryModel
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProductListFragment : Fragment() {

    private val items = ArrayList<CartItemModel>()
    private val adapter = CartItemListAdapter(items)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_product_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        getCartItems()
    }

    private fun getCartItems() {
        val url = "${BuildConfig.BASE_URL}products"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                "success".logErrorMessage()

                val collectionType = object : TypeToken<List<ProductAPIModel>>() {}.type
                val responseJsonArray = Gson().fromJson<List<ProductAPIModel>>(response, collectionType)

                insertProductToRoom(responseJsonArray[0])

                responseJsonArray
                    .groupBy { it.categoryName }
                    .forEach {
                        val categoryModel = CategoryModel(
                            id = it.key,
                            title = it.key,
                            description = it.key
                        )

                        val products = it.value.map { apiModel ->
                            ProductModel(
                                id = apiModel.id,
                                title = apiModel.name,
                                description = apiModel.description
                            )
                        }

                        this.items.add(categoryModel)
                        this.items.addAll(products)
                    }

//                adapter.notifyDataSetChanged()
                adapter.notifyItemRangeInserted(0, this.items.size)

                "llog".logErrorMessage()
            },
            {
                "That didn't work!".logErrorMessage()
            }
        )

        VolleyRequestQueue.addToRequestQueue(stringRequest)
    }

    private fun insertProductToRoom(model: ProductAPIModel) {
        ProductsRepository.insertProduct(model) {
            "product insert success".logErrorMessage()

            //ProductsRepository.getAllProducts()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)

        val rvProducts = view?.findViewById<RecyclerView>(R.id.rv_products) ?: return
        rvProducts.apply {
            this.layoutManager = layoutManager
            this.adapter = this@ProductListFragment.adapter
        }
    }
}