package com.example.coacharea.ui.utils.differs

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.Client

class ClientDiffItemCallback : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem == newItem
    }
}