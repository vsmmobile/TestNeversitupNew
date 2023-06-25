package com.test.Neversitup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {



    var btnConnectApi = MutableLiveData<String>().apply {
        value =""
    }
    val clickButton = MutableLiveData<String>().apply {
        value = null
    }
    fun setClickButton(msg:String?=""){
        clickButton.value = msg
    }



}