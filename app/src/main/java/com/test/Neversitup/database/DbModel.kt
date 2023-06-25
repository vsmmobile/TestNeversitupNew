package com.test.Neversitup.database

sealed class DbModel {

    data class CurrentpriceDetail(
        var id: Int = 0,
        var USD: String,
        var GBP: String,
        var EUR: String = "",
        var Detail: String = "",
        var create_date: String = "",
        var update_date: String = "",

    ) : DbModel()



}
