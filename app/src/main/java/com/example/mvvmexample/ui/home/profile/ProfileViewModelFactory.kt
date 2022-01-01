package com.example.mvvmexample.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.ui.auth.AuthViewModel

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository) as T
    }

}