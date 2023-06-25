package com.test.Neversitup
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.Neversitup.database.ConnectDataBase
import or.th.mobile.librarymobile.common.GobalValue



abstract class FunMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GobalValue.connectDb = ConnectDataBase(this)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onBackPressed() {

    }

    override fun onRestart() {
        super.onRestart()

    }



}