package com.evanshrestha.asdic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editAddress = findViewById<TextView>(R.id.editAddress)
        var editPort = findViewById<TextView>(R.id.editPort)
        var buttonConnect = findViewById<Button>(R.id.buttonConnect)
        var apiKey : String = ""

        val intent = Intent(this, MenuActivity::class.java)

        buttonConnect.setOnClickListener {
            doAsync {
                var url = "http://" + editAddress.text.trim().toString() + ":" + editPort.text.trim().toString() + "/api/system/status?apikey=" + apiKey
                try {
                    val result = URL(url).readText()
                    intent.apply {
                        putExtra("IP", editAddress.text.trim().toString())
                        putExtra("PORT", editPort.text.trim().toString())
                        putExtra("API_KEY", apiKey)
                    }
                    uiThread {
                        toast(result)

                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    uiThread() {
                        toast("Oops, something went wrong")
                    }
                }

            }
        }


    }

    fun onConnect() {

    }
}
