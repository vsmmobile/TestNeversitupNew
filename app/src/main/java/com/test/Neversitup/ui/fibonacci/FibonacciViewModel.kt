package com.test.Neversitup.ui.fibonacci

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FibonacciViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val text = MutableLiveData<String>().apply {
        value =""
    }
    fun setText(msg:String){
        text.value=msg
    }
}