package or.th.mobile.librarymobile.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.test.Neversitup.common.KeyData
import com.test.Neversitup.common.Logger
import java.util.*

object GobalFun {

    fun showToast( activity :Activity,message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
    fun showToast(context  :Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun supString(word: String, indexStart: Int, indexEnd: Int): String {
        if(indexEnd <= word.length ){
            return word.subSequence(indexStart, indexEnd).toString()
        }
      return word
    }

    fun splitWord(str_word: String, str_split: String): ArrayList<String> {
        val parts = str_word.split(str_split)
        print(parts)
        return parts as ArrayList<String>
    }



    @SuppressLint("NewApi")
    fun dateTime(): String {
        var mDate = ""
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.ENGLISH)
            mDate = formatter.format(Date())
            Log.e("mDate", mDate.toString())
        } catch (e: Exception) {
            Log.e("mDate", e.toString()) // this never gets called either
        }
        return mDate.toString()
    }

    fun dateTimeshow(datetime: String): String? {
        var datetime = datetime
        val format = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val format_want = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        try {
            val date = format.parse(datetime)
            return format_want.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.text.ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return ""
    }






}
