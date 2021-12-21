package com.example.mvvmexample.ui.auth

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexample.R
import com.example.mvvmexample.data.db.AppDatabase
import com.example.mvvmexample.data.db.entities.User
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.network.NetworkConnectionInterceptor
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.databinding.ActivityLoginBinding
import com.example.mvvmexample.ui.home.HomeActivity
import com.example.mvvmexample.utils.hide
import com.example.mvvmexample.utils.show
import com.example.mvvmexample.utils.snackBar
import com.example.mvvmexample.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyAPI(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java);

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        findViewById<ProgressBar>(R.id.progress_bar).show()
    }

    override fun onSuccess(user: User) {
        findViewById<ProgressBar>(R.id.progress_bar).hide()
    }

    override fun onFailure(message: String) {
        findViewById<ProgressBar>(R.id.progress_bar).hide()
        findViewById<ConstraintLayout>(R.id.root_layout).snackBar(message)
    }
}