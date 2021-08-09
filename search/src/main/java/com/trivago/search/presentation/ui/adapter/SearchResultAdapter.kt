package com.trivago.search.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivago.domain.search.model.CharacterDomain
import com.trivago.search.databinding.ItemRecentHistoryBinding

class SearchResultAdapter(
    private val results: List<CharacterDomain>,
    private val onClick: (item: CharacterDomain) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemRecentHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size

    class SearchResultViewHolder(
        private val itemBinding: ItemRecentHistoryBinding,
        private val onClick: (item: CharacterDomain) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: CharacterDomain) = with(itemBinding) {
            recentHistoryContainer.setOnClickListener {
                onClick.invoke(item)
            }
            characterRecentHistoryName.text = item.name
        }
    }
}
