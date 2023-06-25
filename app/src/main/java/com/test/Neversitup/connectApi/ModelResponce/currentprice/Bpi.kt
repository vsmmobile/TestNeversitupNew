package com.test.Neversitup.connectApi.ModelResponce.currentprice

import com.google.gson.annotations.SerializedName


data class Bpi (

  @SerializedName("USD" ) var USD : USD? = USD(),
  @SerializedName("GBP" ) var GBP : GBP? = GBP(),
  @SerializedName("EUR" ) var EUR : EUR? = EUR()

)