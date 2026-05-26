package com.nazlasalsabila.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class LatihanAdapter(private val itemList: List<ItemModel>) :
    RecyclerView.Adapter<LatihanAdapter.LatihanViewHolder>() {

    class LatihanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardItem: CardView = itemView.findViewById(R.id.cardItem)
        val tvGambarPlaceholder: TextView = itemView.findViewById(R.id.tvGambarPlaceholder)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val switchItem: SwitchCompat = itemView.findViewById(R.id.switchItem)
        val btnItem: MaterialButton = itemView.findViewById(R.id.btnItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatihanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_latihan, parent, false)
        return LatihanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LatihanViewHolder, position: Int) {
        val item = itemList[position]
        val itemTitle = item.title.lowercase()

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvGambarPlaceholder.text = "Gambar\n${position + 1}"

        if (position == 1 || position == 3) {
            holder.cardItem.setCardBackgroundColor(Color.parseColor("#EAEFDB"))
        } else {
            holder.cardItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        holder.switchItem.setOnCheckedChangeListener(null)
        holder.switchItem.isChecked = item.isSwitchOn

        holder.switchItem.setOnCheckedChangeListener { _, isChecked ->
            item.isSwitchOn = isChecked
            if (isChecked) {
                Toast.makeText(
                    holder.itemView.context,
                    "Switch hidup pada $itemTitle",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.btnItem.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Tombol telah ditekan untuk tombol $itemTitle",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = itemList.size
}