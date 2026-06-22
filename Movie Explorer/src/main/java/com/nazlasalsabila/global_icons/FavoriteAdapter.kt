package com.nazlasalsabila.global_icons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazlasalsabila.global_icons.data.local.FavoriteEntity
import com.nazlasalsabila.global_icons.databinding.ItemFavoriteBinding

class FavoriteAdapter :
    RecyclerView.Adapter<
            FavoriteAdapter.ViewHolder>() {

    private val list =
        mutableListOf<FavoriteEntity>()

    fun submitList(
        data: List<FavoriteEntity>
    ) {

        list.clear()

        list.addAll(
            data
        )

        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding:
        ItemFavoriteBinding
    ) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        fun bind(
            item: FavoriteEntity
        ) {

            binding.tvMovieTitle.text =
                item.title

            Glide
                .with(
                    binding.root
                )
                .load(
                    "https://image.tmdb.org/t/p/w500${item.poster}"
                )
                .into(
                    binding.imgPoster
                )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        return ViewHolder(

            ItemFavoriteBinding.inflate(

                LayoutInflater.from(
                    parent.context
                ),

                parent,

                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.bind(
            list[position]
        )
    }

    override fun getItemCount():
            Int {

        return list.size
    }
}