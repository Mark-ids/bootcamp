package com.qathafiii.bootcamp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.qathafiii.bootcamp.R.id.tv1
import com.qathafiii.bootcamp.network.HttpRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class HomeActivity : AppCompatActivity(), Callback{
    val request : HttpRequest = HttpRequest()
    var tv1:TextView? = null
    var name:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        tv1 = findViewById(R.id.tv1);

        val url = "https://api.github.com/user"
        val credentials = HashMap<String,String>()
        credentials.put("UserName","mmarcel@idscorpgh.com")
        credentials.put("Password","anyThing2018")
        val headers = HashMap<String,String>()
        headers.put("User-Agent","mmarcel@idscorpgh.com")
        request.GET(url,credentials,null,this)
        Thread.sleep(5000L)
        if(name.length > 0){
            findViewById<TextView>(R.id.tv1).setText(name)
        }else{
            findViewById<TextView>(R.id.tv1).setText("response is null")
        }

    }

    override fun onResponse(call: Call?, response: Response?) {
        //Log.e("response",response?.body()?.string())
        val jsonObj = JSONObject(response?.body()?.string() )
        if(jsonObj != null){
            name = jsonObj.getString("login")
            findViewById<TextView>(R.id.tv1)?.setText(name)
        }
        response?.close()
    }

    override fun onFailure(call: Call?, e: IOException?) {
        name = "Failed"
    }





}
