package com.example.mvvmexample.data.network

import androidx.annotation.RawRes
import com.example.mvvmexample.data.network.responses.AuthResponse
import com.google.gson.annotations.JsonAdapter
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyAPI {
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Response<AuthResponse>

    companion object{
        operator  fun  invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyAPI{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://192.168.1.44:3000/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }
}