package com.example.edittextlistdemo.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextlistdemo.databinding.RowEdittextListItemBinding
import com.example.edittextlistdemo.listener.RecyclerItemClickListener
import com.example.edittextlistdemo.model.EditTextItem

class EditTextAdapter(val mContext: Context, val editTextList:ArrayList<EditTextItem>, val listener: RecyclerItemClickListener) :RecyclerView.Adapter<EditTextAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowEdittextListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowEdittextListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return editTextList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = editTextList[holder.adapterPosition]

        holder.binding.apply {
            edtValue.setText(item.value)
            if(item.shouldShowError){
                tvError.visibility = View.VISIBLE
            }else{
                tvError.visibility = View.GONE
            }

            edtValue.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    editTextList.get(holder.adapterPosition).value = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })

            edtValue.setOnEditorActionListener { _, actionId, _ ->
                if (holder.adapterPosition == editTextList.size - 1){
                    edtValue.clearFocus()
                    val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(edtValue.windowToken, 0)
                    true
                }else{
                    false
                }
            }

            ivDelete.setOnClickListener {
                listener.onRecyclerItemClick(it,holder.adapterPosition)
            }
        }
    }
}