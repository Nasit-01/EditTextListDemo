package com.example.edittextlistdemo.listener

import android.view.View

interface RecyclerItemClickListener {
    fun onRecyclerItemClick(view: View,position: Int)
}