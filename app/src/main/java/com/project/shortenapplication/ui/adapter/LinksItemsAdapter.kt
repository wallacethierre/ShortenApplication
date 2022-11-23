package com.project.shortenapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.shortenapplication.R
import com.project.shortenapplication.databinding.ListItemAliasBinding
import com.project.shortenapplication.ui.model.AliasView
import javax.inject.Inject

class LinksItemsAdapter @Inject constructor() :
    ListAdapter<AliasView, LinksItemsAdapter.LinksItemViewHolder>(
        diffCallback
    ) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AliasView>() {
            override fun areItemsTheSame(oldItem: AliasView, newItem: AliasView): Boolean {
                return oldItem.alias == newItem.alias
            }

            override fun areContentsTheSame(oldItem: AliasView, newItem: AliasView): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun addData(linksList: List<AliasView>) {
        submitList(linksList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_alias, parent, false)
        return LinksItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LinksItemViewHolder, position: Int) {
        val link = getItem(position)
        link.let {
            holder.bindItem(link)
        }
    }

    inner class LinksItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListItemAliasBinding.bind(itemView)

        fun bindItem(aliasView: AliasView) {
            binding.originalURL.text = aliasView.selfURL
            binding.shortenedURL.text = aliasView.shortURL
        }
    }
}