package com.example.appiness

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appiness.databinding.ItemSearchBinding
import com.example.appiness.searchreponse.SearchPhotosData


/**
 * adapter class for the search items.
 */
public class SearchAdapter(data: ArrayList<SearchPhotosData>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchData: ArrayList<SearchPhotosData>
    lateinit var mContext: Context

    init {
        searchData = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemSearchBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_search, parent, false)
        return SearchViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (searchData != null) searchData.size else 0
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.mItemBinding.tvTitleData.text = searchData.get(position).title
        holder.mItemBinding.tvId.text =
            """${mContext.resources.getString(R.string.id)}${searchData.get(position).id}"""
        holder.mItemBinding.tvFarm.text =
            """${mContext.resources.getString(R.string.farm)}${searchData.get(position).farm}"""
    }

    /**
     * view holder class for the search items
     */
    class SearchViewHolder(itemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemSearchBinding

        init {
            mItemBinding = itemBinding
        }
    }
}