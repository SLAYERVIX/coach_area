package com.example.coacharea.ui.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coacharea.databinding.ItemRvClientBinding
import com.example.coacharea.ui.home.HomeFragmentDirections
import com.example.coacharea.ui.utils.differs.ClientDiffItemCallback
import com.example.domain.entity.Client

class ClientAdapter : ListAdapter<Client,ClientAdapter.ClientViewHolder>(ClientDiffItemCallback()) {
    inner class ClientViewHolder(private val binding: ItemRvClientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Client) {
            binding.client = item

            Glide.with(itemView)
                .load(item.imageUri)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imageView)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            ItemRvClientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToClientDetailsFragment(getItem(position)))
        }
    }
}