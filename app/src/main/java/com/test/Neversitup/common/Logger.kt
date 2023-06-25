package com.test.Neversitup.common

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import or.th.mobile.librarymobile.common.GobalValue

object Logger {

     var level = Log.DEBUG

     fun debugName(logName: String, msg: String) {
         if (level <= Log.DEBUG) {
             Log.d("debug--->"+logName, msg)
         }
     }

     fun debugAppName(msg: String) {
         if (level <= Log.DEBUG) {
             Log.d("debug--->"+GobalValue.logAppName, msg)
         }
     }

     fun debug(msg: String, tr: Throwable) {
         if (level <= Log.DEBUG) {
             Log.d(GobalValue.logAppName, msg, tr)
         }
     }

     fun info(msg: String) {
         if (level <= Log.INFO) {
             Log.i(GobalValue.logAppName, msg)
         }
     }

     fun info(msg: String, tr: Throwable) {
         if (level <= Log.INFO) {
             Log.i(GobalValue.logAppName, msg, tr)
         }
     }

     fun warn(msg: String) {
         if (level <= Log.WARN) {
             Log.w(GobalValue.logAppName, msg)
         }
     }

     fun warn(msg: String, tr: Throwable) {
         if (level <= Log.WARN) {
             Log.w(GobalValue.logAppName, msg, tr)
         }
     }

     fun error(msg: String) {
         if (level <= Log.ERROR) {
             Log.e(GobalValue.logAppName, msg)
         }
     }

     fun error(msg: String, tr: Throwable) {
         if (level <= Log.ERROR) {
             Log.e(GobalValue.logAppName, msg, tr)
         }
     }


 }