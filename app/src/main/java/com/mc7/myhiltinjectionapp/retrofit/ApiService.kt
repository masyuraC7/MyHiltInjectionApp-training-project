package com.mc7.myhiltinjectionapp.retrofit

import com.mc7.myhiltinjectionapp.DetailUserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users/arip")
    fun getDetailUser(): Call<DetailUserResponse>
}