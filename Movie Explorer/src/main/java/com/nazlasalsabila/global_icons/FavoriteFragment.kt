package com.nazlasalsabila.global_icons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazlasalsabila.global_icons.data.local.DatabaseProvider
import com.nazlasalsabila.global_icons.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FavoriteAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        _binding =
            FragmentFavoriteBinding.bind(
                view
            )

        adapter =
            FavoriteAdapter()

        binding.rvFavorite.layoutManager =
            LinearLayoutManager(
                requireContext()
            )

        binding.rvFavorite.adapter =
            adapter

        val db =
            DatabaseProvider.getDatabase(
                requireContext()
            )

        viewLifecycleOwner.lifecycleScope.launch {

            val list =
                db.movieDao()
                    .getFavorites()

            adapter.submitList(
                list
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}