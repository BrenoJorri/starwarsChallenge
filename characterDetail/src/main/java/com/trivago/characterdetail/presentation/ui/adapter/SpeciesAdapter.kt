package com.trivago.characterdetail.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.trivago.characterdetail.R
import com.trivago.characterdetail.databinding.ItemSpeciesBinding
import com.trivago.domain.detail.model.SpeciesDomain

class SpeciesAdapter(
    private val species: List<SpeciesDomain>
) : RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SpeciesViewHolder(
            ItemSpeciesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) =
        holder.bind(species[position])

    override fun getItemCount() = species.size

    class SpeciesViewHolder(
        private val binding: ItemSpeciesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(speciesDomain: SpeciesDomain) = with(binding) {
            speciesInfoName.text =
                binding.root.context.getString(R.string.character_info_name, speciesDomain.name)
            speciesInfoLanguage.text =
                binding.root.context.getString(R.string.species_language, speciesDomain.language)
        }
    }
}