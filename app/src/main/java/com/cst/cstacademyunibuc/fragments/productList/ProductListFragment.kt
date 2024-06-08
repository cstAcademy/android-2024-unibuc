package com.cst.cstacademyunibuc.fragments.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.MainActivity2
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.adapters.CartItemListAdapter
import com.cst.cstacademyunibuc.databinding.FragmentProductListBinding
import com.cst.cstacademyunibuc.helpers.VolleyRequestQueue
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.managers.SharedPrefsManager
import com.cst.cstacademyunibuc.models.CartItemModel
import com.cst.cstacademyunibuc.models.CategoryModel
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.api.toCartItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductListFragmentListener {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_product_list, container, false)

        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCartItems()

        viewModel.products.observe(viewLifecycleOwner) {
            "list updated".logErrorMessage()
        }

        viewModel.users.observe(viewLifecycleOwner) {
            "users done: ${it?.users?.map { it.username } ?: "null"}".logErrorMessage()
        }

        viewModel.usersAdmin.observe(viewLifecycleOwner) {
            "users done: ${it?.users?.map { it.username } ?: "null"}".logErrorMessage()
        }
    }

    override fun logOut() {
        (activity as? MainActivity2)?.apply {
            this.logout()
        }
    }
}

interface ProductListFragmentListener {
    fun logOut()
}