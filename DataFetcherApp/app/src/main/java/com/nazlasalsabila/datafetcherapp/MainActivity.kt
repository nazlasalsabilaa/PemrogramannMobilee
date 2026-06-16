package com.nazlasalsabila.datafetcherapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val client = HttpClient(CIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textViewResult)

        lifecycleScope.launch {
            try {
                val jsonString = client
                    .get("https://nazla-api.free.beeceptor.com/data")
                    .bodyAsText()

                val moshi = Moshi.Builder().build()
                val adapter = moshi.adapter(ApiResponse::class.java)

                val response = adapter.fromJson(jsonString)

                textView.text =
                    "API RESPONSE\n\n" +
                            "Message : ${response?.message}\n" +
                            "Code        : ${response?.code}\n" +
                            "Data         : ${response?.data?.joinToString(", ")}"

            } catch (e: Exception) {
                textView.text = "Error: ${e.message}"
            }
        }
    }
}