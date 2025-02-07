package com.example.edittextlistdemo

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edittextlistdemo.adapter.EditTextAdapter
import com.example.edittextlistdemo.adapter.EditTextDataAdapter
import com.example.edittextlistdemo.databinding.ActivityMainBinding
import com.example.edittextlistdemo.databinding.ActivityResultBinding
import com.example.edittextlistdemo.listener.RecyclerItemClickListener
import com.example.edittextlistdemo.model.EditTextItem

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var editTextDataList = ArrayList<EditTextItem>()
    private var adapter: EditTextDataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextDataList.clear()
        editTextDataList.addAll(intent.getParcelableArrayListExtra("editTextList")!!)

        initViews()
    }

    private fun initViews(){

        adapter = EditTextDataAdapter(editTextDataList)
        binding.rvDataList.layoutManager = LinearLayoutManager(this)
        binding.rvDataList.adapter = adapter

    }
}