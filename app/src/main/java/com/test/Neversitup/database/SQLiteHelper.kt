package com.test.Neversitup.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.test.Neversitup.common.Logger
import java.io.File
import java.io.IOException
import java.io.FileOutputStream as FileOutputStream1

class SQLiteHelper(private val context: Context) {

  /*  companion object {

        private val DB_NAME = "pm4100.db"
    }*/
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "demoDB.db"
      private  val logName="#DB"

    }
    fun createDataBase(): SQLiteDatabase {
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            try {
                val checkDB = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null)
                checkDB?.close()
                copyDatabase(dbFile)
            }catch (e: IOException) {
                throw RuntimeException("Error creating source database", e)
            }

        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    @SuppressLint("WrongConstant")
    private fun copyDatabase(dbFile: File) {
        val openAsset = context.assets.open(DATABASE_NAME)
        val os = FileOutputStream1(dbFile)

        val buffer = ByteArray(1024)
        while (openAsset.read(buffer) > 0) {
            os.write(buffer)
            Logger.debugName(logName, "writing>>")
        }

        os.flush()
        os.close()
        openAsset.close()
        Logger.debugName(logName, "completed..")
    }
    @Throws(IOException::class)
    private fun copyDataBase1() {
        try{
            //val outFileName = DATABASE_PATH + DATABASE_NAME
            val outFileName = context.getDatabasePath(DATABASE_NAME)

            val myOutput = FileOutputStream1(outFileName)
            val myInput = context.assets.open(DATABASE_NAME)

            val buffer = ByteArray(1024)
            var length: Int = myInput.read(buffer)
            while ((length) > 0) {
                myOutput.write(buffer, 0, length)
                length = myInput.read(buffer)
            }
            myInput.close()
            myOutput.flush()
            myOutput.close()
        }catch (e:IOException){

        }

    }
    fun getWritableDatabase_sdcard(): SQLiteDatabase? {
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        return SQLiteDatabase.openDatabase(dbFile.toString(), null, SQLiteDatabase.OPEN_READWRITE)
        //return SQLiteDatabase.openDatabase(DB_PATH + DB_NAME + ".db", null, SQLiteDatabase.OPEN_READWRITE);
    }

    fun getReadableDatabase_sdcard(): SQLiteDatabase? {
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        return SQLiteDatabase.openDatabase(dbFile.toString(), null, SQLiteDatabase.OPEN_READONLY)


    }

}
