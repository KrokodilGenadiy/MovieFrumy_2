package com.zaus_app.moviefrumy_20.view.rv_adaptes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.zaus_app.moviefrumy_2.databinding.FilmItemBinding
import com.zaus_app.moviefrumy_2.domain.Film
import com.zaus_app.moviefrumy_2.view.rv_adaptes.FilmDiffCallBack
import com.zaus_app.moviefrumy_20.view.rv_viewholders.FilmViewHolder

class FilmsAdapter(private val clickListener: OnItemClickListener) : PagingDataAdapter<Film, FilmViewHolder>(
    FilmDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding) {
            clickListener.click(it)
        }
    }
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    interface OnItemClickListener {
        fun click(position: Int)
    }
}