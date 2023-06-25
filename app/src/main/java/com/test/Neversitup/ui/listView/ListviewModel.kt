package com.test.Neversitup.ui.listView



sealed class ListviewModel {
    data class DataServiceShow(
        var dateTime: String,
        var USD: String,
        var GBP: String,
        var EUR: String,
    ) : ListviewModel()
}

