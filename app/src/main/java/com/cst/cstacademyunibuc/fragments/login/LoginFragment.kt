package com.cst.cstacademyunibuc.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.StringRequest
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.databinding.FragmentLoginBinding
import com.cst.cstacademyunibuc.helpers.VolleyRequestQueue
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.models.LoginModel

class LoginFragment : Fragment(), LoginFragmentListener {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)

        binding.listener = this
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(BuildConfig.DEBUG) {
            //viewModel.username.value = "mor_2314"
            viewModel.password.set("83r5^_")
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loginModel.observe(viewLifecycleOwner) { loginModel ->
            doLogin(loginModel)
        }
    }

    private fun doLogin(loginModel: LoginModel) {
        val url = "${BuildConfig.BASE_URL}auth/login"

        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            { response ->
                "success".logErrorMessage()

                goToProducts()
            },
            {
                "That didn't work!".logErrorMessage()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["username"] = loginModel.username
                params["password"] = loginModel.password
                return params
            }
        }

        stringRequest.tag = "SRTAG"

        VolleyRequestQueue.addToRequestQueue(stringRequest)
    }

    override fun goToForgotPassword() {
        //
    }

    override fun goToRegister() =
        findNavController().navigate(LoginFragmentDirections.actionFragmentLoginToRegisterFragment())

    private fun goToProducts() =
        findNavController().navigate(LoginFragmentDirections.actionFragmentLoginToProductListFragment())

    override fun onStop() {
        super.onStop()

        VolleyRequestQueue.requestQueue.cancelAll("SRTAG")
    }
}

interface LoginFragmentListener {
    fun goToForgotPassword()
    fun goToRegister()
}