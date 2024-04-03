package com.cst.cstacademyunibuc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.StringRequest
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.helpers.VolleyRequestQueue
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goToRegisterBtn = view.findViewById<Button>(R.id.btn_register)
        goToRegisterBtn.setOnClickListener {
            goToRegister()
        }

        val doLoginBtn = view.findViewById<Button>(R.id.btn_login)
        doLoginBtn.setOnClickListener { doLogin() }
    }


    private fun doLogin() {
        val edtUsername = view?.findViewById<EditText>(R.id.et_username) ?: return
        val edtPassword = view?.findViewById<EditText>(R.id.et_password) ?: return

        val username: String
        val password: String

        when (BuildConfig.DEBUG) {
            true -> {
                username = "mor_2314"
                password = "83r5^_"
            }

            false -> {
                username = edtUsername.text.toString().trim()
                password = edtPassword.text.toString().trim()
            }
        }

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
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag = "SRTAG"

        VolleyRequestQueue.addToRequestQueue(stringRequest)
    }

    private fun goToRegister() =
        findNavController().navigate(LoginFragmentDirections.actionFragmentLoginToRegisterFragment())

    private fun goToProducts() =
        findNavController().navigate(LoginFragmentDirections.actionFragmentLoginToProductListFragment())

    override fun onStop() {
        super.onStop()

        VolleyRequestQueue.requestQueue.cancelAll("SRTAG")
    }
}