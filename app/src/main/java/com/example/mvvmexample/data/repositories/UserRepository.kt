package com.example.mvvmexample.data.repositories

import com.example.mvvmexample.data.db.AppDatabase
import com.example.mvvmexample.data.db.entities.User
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.network.SafeApiRequest
import com.example.mvvmexample.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
        private val api: MyAPI,
        private val db: AppDatabase
        ) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()
}