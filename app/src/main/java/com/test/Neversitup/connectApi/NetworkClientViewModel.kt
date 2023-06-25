package com.test.Neversitup.connectApi

import androidx.lifecycle.ViewModel

import com.test.Neversitup.common.Logger
import com.test.Neversitup.connectApi.ModelResponce.ResponseApp
import kotlinx.coroutines.*
import or.th.mobile.librarymobile.common.*
import java.util.*

import kotlin.collections.ArrayList

class NetworkClientViewModel : ViewModel() {
    val logNamePage="networkClientViewModel--->"
    suspend fun sendDataToApi(objRequest: Any? = null, methodsApi: String): ResponseApp {
        var objResponse = ResponseApp("N000", "No")
        val job = GlobalScope.launch {
            val result = withTimeout(GobalValue.timeOut) {

                Logger.debugName(logNamePage,"------sendDataToApi InsertLog success------")
                when (methodsApi) {
                    GobalValue.methodsApiCurrentprice -> {
                        objResponse = getDataCurrentprice()
                    }
                }

            }

            Logger.debugName(logNamePage,"------sendDataToApi Out with Timeout------$result")
        }
        job.join()
        if (job.isCancelled) {
            objResponse = ResponseApp("T000", "TimeOut")
            Logger.debugName(logNamePage,"sendDataToApi TimeOut Job was cancelled.--->")

        }
        return objResponse
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun getDataCurrentprice(): ResponseApp {
        val data = NetworkClient().getCurrentpriceApi()
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            Logger.debugName(logNamePage,"connect message--->" + data.obj)
        }
        return data
    }




}
