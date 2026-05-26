package com.nazlasalsabila.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazlasalsabila.recyclerview.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvLatihan: RecyclerView = findViewById(R.id.rvLatihan)

        val listData = listOf(
            ItemModel(1, "Item Pertama", "Deskripsi item pertama", R.mipmap.ic_launcher),
            ItemModel(2, "Item Kedua", "Deskripsi item kedua", R.mipmap.ic_launcher),
            ItemModel(3, "Item Ketiga", "Deskripsi item ketiga", R.mipmap.ic_launcher),
            ItemModel(4, "Item Keempat", "Deskripsi item keempat", R.mipmap.ic_launcher),
            ItemModel(5, "Item Kelima", "Deskripsi item kelima", R.mipmap.ic_launcher)
        )

        rvLatihan.layoutManager = LinearLayoutManager(this)

        rvLatihan.adapter = LatihanAdapter(listData)
    }
}
