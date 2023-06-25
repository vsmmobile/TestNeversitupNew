package com.test.Neversitup.database

import androidx.lifecycle.MutableLiveData
import or.th.mobile.librarymobile.common.GobalValue


/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class DatabaseRepository() {
    private var  insertData = MutableLiveData<Boolean>()
    /*select*/
  fun selectAllCurrentpriceMutableLiveData():MutableLiveData<List<DbModel.CurrentpriceDetail>> {
      var selectData = MutableLiveData<List<DbModel.CurrentpriceDetail>>()
      val data: List<DbModel.CurrentpriceDetail> = GobalValue.connectDb!!.selectCurrentprice()
      selectData.value = data
      return selectData
  }


   /*insert*/
    fun insertCurrentpriceDetailMutableLiveData(obj: DbModel.CurrentpriceDetail):MutableLiveData<Boolean>  {
        val data: Boolean = GobalValue.connectDb!!.insertCurrentprice(obj)
       insertData.value = data
        return insertData
    }
}
