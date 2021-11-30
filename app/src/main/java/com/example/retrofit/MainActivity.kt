package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.lang.IllegalStateException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val updateTimeText: TextView = findViewById(R.id.update_time)
        val statusText: TextView = findViewById(R.id.status)
        var data =""

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sheetdb.io/api/v1/rp1serrhhbdb3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        thread {
            try {
                val service: DateApiInterface = retrofit.create(DateApiInterface::class.java)

                val dateApiResponse = service.getDate().execute().body()
                    ?: throw IllegalStateException("bodyがnullだよ")

                Handler(Looper.getMainLooper()).post {
                    Log.d("response", dateApiResponse[0].update_time.toString())
                    updateTimeText.text = dateApiResponse[0].update_time.toString()
                    statusText.text = dateApiResponse[0].status.toString()
                }
            } catch (e: Exception) {
                Log.e("Error", "${e}")
            }
        }
    }
}