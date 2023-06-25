package or.th.mobile.librarymobile.common

import android.annotation.SuppressLint
import com.test.Neversitup.database.ConnectDataBase

object GobalValue {
  //Api
  var url: String = "https://api.coindesk.com/v1/"
  const val timeOut: Long = 60000L
  const val methodsApiCurrentprice: String = "currentprice"


  //App

  @SuppressLint("StaticFieldLeak")
  var connectDb: ConnectDataBase? = null
  const val logAppName: String = "NeversitupLog"



}
