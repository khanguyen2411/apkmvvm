package com.example.mvvmexample.data.network.responses

import com.example.mvvmexample.data.db.entities.User

data class AuthResponse (
        val isSuccessful: Boolean?,
        val message: String?,
        val user: User?
        )