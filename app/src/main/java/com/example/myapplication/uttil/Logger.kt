package com.example.myapplication.uttil

import android.util.Log
import com.example.myapplication.uttil.Constants.DEBUG
import com.example.myapplication.uttil.Constants.TAG

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}

