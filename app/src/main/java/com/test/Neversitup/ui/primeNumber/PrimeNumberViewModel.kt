package com.test.Neversitup.ui.primeNumber

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrimeNumberViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val text = MutableLiveData<String>().apply {
        value ="0"
    }
    fun setText(msg:String){
        text.value=msg
    }
}