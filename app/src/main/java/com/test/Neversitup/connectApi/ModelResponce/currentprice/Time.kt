package com.test.Neversitup.connectApi.ModelResponce.currentprice

import com.google.gson.annotations.SerializedName


data class Time (

  @SerializedName("updated"    ) var updated    : String? = null,
  @SerializedName("updatedISO" ) var updatedISO : String? = null,
  @SerializedName("updateduk"  ) var updateduk  : String? = null

)