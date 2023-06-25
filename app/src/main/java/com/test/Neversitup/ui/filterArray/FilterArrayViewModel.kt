package com.test.Neversitup.ui.filterArray

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterArrayViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val text = MutableLiveData<String>().apply {
        value =""
    }
    fun setText(msg:String){
        text.value=msg
    }
}