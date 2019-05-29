package com.qathafiii.bootcamp.network

import android.util.Base64
import android.util.Log
import okhttp3.*

implementation 'com.squareup.okhttp3:okhttp:3.10.0'
class HttpRequest {
    val client : OkHttpClient = OkHttpClient()

    fun GET(url : String,credentials: HashMap<String,String>?, headers : HashMap<String,String>?, callback : Callback) {
        val request = Request.Builder().url(url)
        if(headers != null){
            val it = headers.entries.iterator()
            while (it.hasNext()) {
                val pair = it.next() as Map.Entry<*, *>
                request.addHeader(pair.key.toString(), pair.value.toString())
            }
        }
        if(credentials != null){
            request.addHeader("Authorization",Credentials.basic(credentials?.get("UserName"),credentials?.get("Password")))
        }

        Log.e("Base64",Credentials.basic(credentials?.get("UserName"),credentials?.get("Password")))



        client.newCall(request.build()).enqueue(callback)

    }
}