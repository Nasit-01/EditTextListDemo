package com.example.edittextlistdemo.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextlistdemo.databinding.RowDataItemBinding
import com.example.edittextlistdemo.databinding.RowEdittextListItemBinding
import com.example.edittextlistdemo.listener.RecyclerItemClickListener
import com.example.edittextlistdemo.model.EditTextItem

class EditTextDataAdapter(val editTextDataList:ArrayList<EditTextItem>) :RecyclerView.Adapter<EditTextDataAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowDataItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return editTextDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = editTextDataList[holder.adapterPosition]

        holder.binding.apply {
            tvValue.setText(item.value)
            tvSrNo.setText((position+1).toString())
        }
    }
}