package com.test.Neversitup.connectApi

import com.test.Neversitup.connectApi.ModelResponce.currentprice.ResponceCurrentprice
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MethodsApi {

    @Headers("Content-Type: application/json")
    @GET("bpi/currentprice.json")
    fun getCurrentprice(): Call<ResponceCurrentprice>

}
