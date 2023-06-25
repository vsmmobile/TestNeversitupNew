package com.test.Neversitup.connectApi

import com.test.Neversitup.common.Logger
import com.test.Neversitup.connectApi.ModelResponce.ResponseApp
import com.test.Neversitup.connectApi.ModelResponce.currentprice.ResponceCurrentprice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import or.th.mobile.librarymobile.common.GobalValue
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkClient {
    val logNamePage="networkClient--->"
    private fun connectHttps(): MethodsApi {
        Logger.debugName(logNamePage,"connectHttps: -------> " + GobalValue.url)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(GobalValue.url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MethodsApi::class.java)
    }



    suspend fun getCurrentpriceApi(): ResponseApp =
        withContext(Dispatchers.IO) {
            return@withContext suspendCoroutine { continuation ->
                Logger.debugName(logNamePage,"=====================================getCurrentpriceApi===========================================")
                val methodsApi = connectHttps()
                methodsApi.getCurrentprice()
                    .enqueue(object : Callback<ResponceCurrentprice> {
                        override fun onResponse(
                            call: Call<ResponceCurrentprice>,
                            response: Response<ResponceCurrentprice>
                        ) {
                            val data = response.body()
                            val codeApi = response.code()
                            Logger.debugAppName("connect  Response ServiceApi  Data--->" + data.toString())
                            val res = ResponseApp(code = "C000", codeApi = "A000", message = "", obj = data)
                            continuation.resume(res)
                        }

                        override fun onFailure(call: Call<ResponceCurrentprice>, t: Throwable) {
                          val  res = ResponseApp(
                                code = "E000",
                                codeApi = "",
                                message = "Failed to make HTTPS request:"+t.message.toString()
                            )
                            continuation.resume(res)
                        }
                    })

            }
        }



}
