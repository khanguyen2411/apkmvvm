package com.example.mvvmexample.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmexample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}