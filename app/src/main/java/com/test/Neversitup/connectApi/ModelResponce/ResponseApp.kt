package com.test.Neversitup.connectApi.ModelResponce

import org.json.JSONArray
import org.json.JSONObject


class ResponseApp(
    var code: String? = null,
    var codeApi: String? = null,
    var message: String? = null,
    var obj: Any? = null,
    var json_obj: JSONObject? = null,
    var json_arr: JSONArray? = null)
