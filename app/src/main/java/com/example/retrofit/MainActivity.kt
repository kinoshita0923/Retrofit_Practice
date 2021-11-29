package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlin.concurrent.thread


data class DateProperty(
    val id: String?,
    val update_time: String?,
    val status: String?
)

interface DateApiInterface {
    @GET("/")
    fun getDate(): Call<DateProperty>
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val updateTimeText: TextView = findViewById(R.id.update_time)
        val statusText: TextView = findViewById(R.id.status)

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://sheetdb.io/api/v1/rp1serrhhbdb3/")
        }.build()

        thread {
            try {
                val service = retrofit.create(DateApiInterface::class.java)

                val get = service.getDate().execute().body()

                updateTimeText.text = get?.update_time.toString()
                statusText.text = get?.status.toString()
            } catch (e: Exception) {
            }
        }
    }
}