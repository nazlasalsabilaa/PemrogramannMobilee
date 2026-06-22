package com.nazlasalsabila.global_icons

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.nazlasalsabila.global_icons.data.local.AppDatabase
import com.nazlasalsabila.global_icons.data.local.DatabaseProvider
import com.nazlasalsabila.global_icons.data.local.FavoriteEntity
import com.nazlasalsabila.global_icons.data.local.MovieEntity
import com.nazlasalsabila.global_icons.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        db = DatabaseProvider.getDatabase(requireContext())

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("extra_icon", GlobalIcon::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("extra_icon")
        }

        movie?.let { selectedMovie ->
            binding.tvDetailName.text = selectedMovie.name
            binding.tvDetailLocation.text = "${getString(R.string.label_release)} ${selectedMovie.releaseDate}"
            binding.tvDetailDescription.text = selectedMovie.overview.ifEmpty { getString(R.string.desc_unavailable) }

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${selectedMovie.photoUrl}")
                .into(binding.imgDetailPhoto)

            val btnFavorite: ImageButton = binding.btnFavorite
            btnFavorite.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val favorite = db.movieDao().getFavorites()
                    val isExist = favorite.any { it.movieId == selectedMovie.id }

                    if (isExist) {
                        Toast.makeText(requireContext(), getString(R.string.toast_already_favorite), Toast.LENGTH_SHORT).show()
                    } else {
                        db.movieDao().insertMovie(
                            MovieEntity(
                                movieId = selectedMovie.id,
                                title = selectedMovie.name,
                                poster = selectedMovie.photoUrl ?: "",
                                rating = selectedMovie.voteAverage
                            )
                        )
                        db.movieDao().insertFavorite(
                            FavoriteEntity(
                                movieId = selectedMovie.id,
                                title = selectedMovie.name,
                                poster = selectedMovie.photoUrl ?: ""
                            )
                        )
                        Toast.makeText(requireContext(), getString(R.string.toast_added_favorite), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}