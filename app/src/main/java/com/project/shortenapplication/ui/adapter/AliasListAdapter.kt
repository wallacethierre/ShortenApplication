package com.project.shortenapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.shortenapplication.R
import com.project.shortenapplication.databinding.ListItemAliasBinding
import com.project.shortenapplication.ui.model.AliasView
import javax.inject.Inject

class AliasListAdapter @Inject constructor() :
    ListAdapter<AliasView, AliasListAdapter.AliasItemViewHolder>(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AliasItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_alias, parent, false)
        return AliasItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AliasItemViewHolder, position: Int) {
        val link = getItem(position)
        link.let {
            holder.bindItem(link)
        }
    }

    inner class AliasItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListItemAliasBinding.bind(itemView)

        fun bindItem(aliasView: AliasView) {
            binding.originalURL.text = aliasView.selfURL
            binding.shortenedURL.text = aliasView.shortURL
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val dividerItems = DividerItemDecoration(recyclerView.context,
            DividerItemDecoration.VERTICAL)
        getDrawable(recyclerView.context, R.drawable.item_list_divider)?.let { dividerItems.setDrawable(it) }
        recyclerView.addItemDecoration(
            dividerItems
        )
        super.onAttachedToRecyclerView(recyclerView)
    }
}