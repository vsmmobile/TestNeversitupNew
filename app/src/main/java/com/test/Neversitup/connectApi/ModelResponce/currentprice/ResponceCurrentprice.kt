package com.test.Neversitup.connectApi.ModelResponce.currentprice

import com.google.gson.annotations.SerializedName


data class ResponceCurrentprice (

  @SerializedName("time"       ) var time       : Time?   = Time(),
  @SerializedName("disclaimer" ) var disclaimer : Any? = null,
  @SerializedName("chartName"  ) var chartName  : Any? = null,
  @SerializedName("bpi"        ) var bpi        : Bpi?    = Bpi()

)