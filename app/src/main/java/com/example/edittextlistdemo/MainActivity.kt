package com.example.edittextlistdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextlistdemo.adapter.EditTextAdapter
import com.example.edittextlistdemo.databinding.ActivityMainBinding
import com.example.edittextlistdemo.listener.RecyclerItemClickListener
import com.example.edittextlistdemo.model.EditTextItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var editTextList = ArrayList<EditTextItem>()
    private var adapter: EditTextAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        manageVisibility()
        adapter = EditTextAdapter(this,editTextList,object :RecyclerItemClickListener{
            override fun onRecyclerItemClick(view: View, position: Int) {
                if (view.id == R.id.ivDelete){
                    editTextList.removeAt(position)
                    adapter?.notifyItemRemoved(position)
                    manageVisibility()
                }
            }
        })
        binding.rvEditTextList.layoutManager = LinearLayoutManager(this)
        binding.rvEditTextList.adapter = adapter

        binding.btnAdd.setOnClickListener {
            editTextList.add(EditTextItem())
            adapter?.notifyItemInserted(editTextList.size-1)
            manageVisibility()

        }

        binding.btnSubmit.setOnClickListener {
            Log.e("TEST",editTextList.toString())
            if (validateInput()){
                val intent = Intent(this,ResultActivity::class.java)
                intent.putParcelableArrayListExtra("editTextList",editTextList)
                startActivity(intent)
            }
        }

        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val movedItem = editTextList.removeAt(fromPosition)
                editTextList.add(toPosition,movedItem)
                adapter?.notifyItemMoved(fromPosition,toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.rvEditTextList)
    }

    private fun validateInput():Boolean{
        var isValid = true

        editTextList.forEachIndexed { index, editTextItem ->
            val text = editTextItem.value.trim()
            if(text.isEmpty() || text.length < 6 || text.length > 10){
                editTextItem.shouldShowError = true
                isValid = false
            }else{
                editTextItem.shouldShowError = false
            }
        }
        adapter?.notifyDataSetChanged()
        return isValid
    }
    private fun manageVisibility(){
        if (editTextList.isEmpty()){
            binding.rvEditTextList.visibility = View.GONE
            binding.tvEmptyView.visibility = View.VISIBLE
        }else{
            binding.rvEditTextList.visibility = View.VISIBLE
            binding.tvEmptyView.visibility = View.GONE
        }
    }
}