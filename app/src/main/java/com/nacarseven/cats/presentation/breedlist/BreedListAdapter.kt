package com.nacarseven.cats.presentation.breedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nacarseven.cats.R
import com.nacarseven.cats.databinding.FragmentBreedItemBinding
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.presentation.viewBinding

class BreedlListAdapter(
    private val onItemClick: (Breed) -> Unit
) : ListAdapter<Breed, BreedItemViewHolder>(BreedListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BreedItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_breed_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: BreedItemViewHolder, position: Int) =
        holder.bind(currentList[position], onItemClick)
}

class BreedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FragmentBreedItemBinding by viewBinding()

    fun bind(item: Breed, onItemClick: (Breed) -> Unit) = with(binding) {

        breedName.text = item.name
        countryName.text = item.origin
        temperament.text = item.temperament

        root.setOnClickListener { onItemClick.invoke(item) }
    }
}

private class BreedListDiff : DiffUtil.ItemCallback<Breed>() {
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return newItem == oldItem
    }
}