package com.test.Neversitup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.Neversitup.database.DatabaseRepository
import com.test.Neversitup.database.DbModel

class HomeViewModel : ViewModel() {

    val databaseRepository= DatabaseRepository()

    val textUsd = MutableLiveData<String>().apply {
        value =""
    }
    fun setTextUsd(msg:String){
        textUsd.value=msg
    }

    val textGBP = MutableLiveData<String>().apply {
        value ="0"
    }
    fun setTextGBP(msg:String){
        textGBP.value=msg
    }

    val textEUR = MutableLiveData<String>().apply {
        value ="0"
    }

    fun setTextEUR(msg:String){
        textEUR.value=msg
    }
    val textBTC = MutableLiveData<String>().apply {
        value ="0"
    }

    fun setTextBTC(msg:String){
        textBTC.value=msg
    }

    fun selectCurrentprice(): LiveData<List<DbModel.CurrentpriceDetail>> {
        return databaseRepository.selectAllCurrentpriceMutableLiveData()
    }

    fun insertCurrentprice(obj: DbModel.CurrentpriceDetail): LiveData<Boolean> {
        return databaseRepository.insertCurrentpriceDetailMutableLiveData(obj)
    }


}