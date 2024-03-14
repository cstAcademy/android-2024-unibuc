package com.cst.cstacademyunibuc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cst.cstacademyunibuc.R

class LoginFragment: Fragment() {

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
    }

    private fun goToRegister() {
        val action = LoginFragmentDirections.actionFragmentLoginToRegisterFragment()
        findNavController().navigate(action)
    }
}