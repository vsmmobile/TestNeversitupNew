package com.test.Neversitup.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.test.Neversitup.common.KeyData
import com.test.Neversitup.common.Logger
import or.th.mobile.librarymobile.common.*
import java.io.IOException

public class ConnectDataBase {
    private var mContext: Context? = null
    private var dbhelper: SQLiteHelper? = null
    var TAG = "SQLite Database"

    constructor(context: Context?) {
        mContext = context
        dbhelper = SQLiteHelper(mContext!!)
    }

    @Throws(SQLException::class)
    fun createDatabase(): ConnectDataBase? {
        try {
            dbhelper!!.createDataBase()
        } catch (mIOException: IOException) {
            Logger.debugName(TAG, "$mIOException  UnableToCreateDatabase")
            throw Error("UnableToCreateDatabase")
        }
        return this
    }

    fun Read_database(): SQLiteDatabase? {
        val db: SQLiteDatabase? = dbhelper!!.getReadableDatabase_sdcard()
        Logger.debugName(TAG, "--------------read database------------------")
        return db
    }

    fun Write_database(): SQLiteDatabase? {
        val db: SQLiteDatabase? = dbhelper!!.getWritableDatabase_sdcard()
        Logger.debugName(TAG, "--------------write database------------------")
        return db
    }

    @RequiresApi(Build.VERSION_CODES.N)
    open fun insertCurrentprice(obj: DbModel.CurrentpriceDetail): Boolean {
        var numrow: Int = 0
        var db = Write_database()
        try {

            var dateTime = GobalFun.dateTime()//2021-05-28 17:53:36
               var content = ContentValues()
                content.put(KeyData.CurrentpriceDetail_USD, obj.USD)
                content.put(KeyData.CurrentpriceDetail_GBP, obj.GBP)
                content.put(KeyData.CurrentpriceDetail_EUR, obj.EUR)
                content.put(KeyData.CurrentpriceDetail_Detail, obj.Detail)
                content.put(KeyData.CurrentpriceDetail_create_date, dateTime)
                content.put(KeyData.CurrentpriceDetail_update_date, dateTime)


                var columnHack =
                    KeyData.CurrentpriceDetail_USD + "," + KeyData.CurrentpriceDetail_GBP + "," + KeyData.CurrentpriceDetail_EUR + ","
                KeyData.CurrentpriceDetail_Detail + "," + KeyData.CurrentpriceDetail_create_date + "," + KeyData.CurrentpriceDetail_update_date
                numrow = db!!.insert(KeyData.Table_CurrentpriceDetail, columnHack, content).toInt()




        } catch (e: Exception) {
            e.message?.let { Logger.debugName(TAG, it) }
            return false
        } finally {
            Log.d(TAG, " insert success")
        }
        return numrow != 0
    }

    @SuppressLint("Range")
    fun selectCurrentprice(): List<DbModel.CurrentpriceDetail> {

        val conList: ArrayList<DbModel.CurrentpriceDetail> = ArrayList<DbModel.CurrentpriceDetail>()
        val selectQuery =
            "SELECT  * FROM " + KeyData.Table_CurrentpriceDetail + " ORDER BY "+KeyData.CurrentpriceDetail_create_date +"  DESC "
        var db = Read_database()
        var cursor: Cursor? = null
        try {
            cursor = db!!.rawQuery(selectQuery, null)
            var Id: Int
            if (cursor.moveToFirst()) {
                do {
                    Id = cursor.getInt(cursor.getColumnIndex(KeyData.CurrentpriceDetail_id))
                    val con = DbModel.CurrentpriceDetail(
                        id = Id,
                        USD = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_USD)),
                        GBP = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_GBP)),
                        EUR = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_EUR)),
                        Detail = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_Detail)),
                        create_date = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_create_date)),
                        update_date = cursor.getString(cursor.getColumnIndex(KeyData.CurrentpriceDetail_update_date)),
                    )
                    conList.add(con)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.message?.let { Logger.debugAppName(it) }
            db!!.execSQL(selectQuery)
            return ArrayList()

        }
        return conList
    }


}


