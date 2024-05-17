package com.cst.cstacademyunibuc.fragments.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.android.volley.BuildConfig
import com.cst.cstacademyunibuc.helpers.SingleLiveEvent
import com.cst.cstacademyunibuc.models.LoginModel

class LoginFragmentViewModel: ViewModel() {

    val logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Google_Images_2015_logo.svg/1200px-Google_Images_2015_logo.svg.png"

    val username = MutableLiveData<String>()
    val password = ObservableField<String>()

    val isUsernameError: LiveData<Boolean> = username.switchMap { username ->
        val isUsernameValid = username.length <= 3
        MutableLiveData(isUsernameValid)
    }

    val loginModel = SingleLiveEvent<LoginModel>()

    fun generateLoginModel() {
        val username = this.username.value ?: ""
        val password = this.password.get() ?: ""

        loginModel.value = LoginModel(username, password)
    }

}