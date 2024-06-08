package com.cst.cstacademyunibuc.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.databinding.FragmentLoginBinding
import com.cst.cstacademyunibuc.databinding.FragmentRegisterBinding
import com.cst.cstacademyunibuc.fragments.login.LoginFragmentDirections
import com.cst.cstacademyunibuc.fragments.login.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register, container, false)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewModel.userModel.observe(viewLifecycleOwner) {
            goToProducts()
        }
    }

    private fun goToProducts() =
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToNavGraph())
}