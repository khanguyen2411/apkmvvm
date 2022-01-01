package com.example.mvvmexample.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmexample.data.repositories.UserRepository

class ProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val user = userRepository.getUser()
}