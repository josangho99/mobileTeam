package com.example.teamproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.SearchCategoryBinding

class SearchitemAdapter(private var dataset: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(binding: SearchCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.searchCategory
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SearchCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        if (holder is ViewHolder) {
            holder.textView.text = item
        }
    }
}