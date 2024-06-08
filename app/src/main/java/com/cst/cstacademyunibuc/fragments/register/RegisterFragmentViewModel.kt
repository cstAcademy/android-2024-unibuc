package com.cst.cstacademyunibuc.fragments.register

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.cst.cstacademyunibuc.data.repositories.users.UserRepository
import com.cst.cstacademyunibuc.models.user.RoleType
import com.cst.cstacademyunibuc.models.user.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val logoUrl =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Google_Images_2015_logo.svg/1200px-Google_Images_2015_logo.svg.png"

    val username = MutableLiveData<String>()
    val password = ObservableField<String>()

    val isUsernameError: LiveData<Boolean> = username.switchMap { username ->
        val isUsernameValid = username.length <= 3
        MutableLiveData(isUsernameValid)
    }

    val userModel = MutableLiveData<UserModel>()

    fun registerUser() {
        val username = this.username.value ?: ""
        val password = this.password.get() ?: ""

        val user = UserModel(
            username = username,
            password = password,
            role = RoleType.USER_PLEB
        )

        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUserWithRole(user)

            viewModelScope.launch(Dispatchers.Main) {
                this@RegisterFragmentViewModel.userModel.value = user
            }
        }
    }
}