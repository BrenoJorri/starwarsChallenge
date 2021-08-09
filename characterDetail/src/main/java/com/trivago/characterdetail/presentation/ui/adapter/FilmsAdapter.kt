package com.trivago.characterdetail.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivago.characterdetail.databinding.ItemFilmsBinding
import com.trivago.domain.detail.model.FilmDomain

class FilmsAdapter(
    private val films: List<FilmDomain>
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmsViewHolder(
            ItemFilmsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) =
        holder.bind(films[position])

    override fun getItemCount() = films.size

    class FilmsViewHolder(private val binding: ItemFilmsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmDomain: FilmDomain) = with(binding) {
            filmsInfoName.text = filmDomain.title
            filmsInfoDescription.text = filmDomain.description
        }
    }

}