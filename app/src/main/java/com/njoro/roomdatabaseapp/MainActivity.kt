package com.njoro.roomdatabaseapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.njoro.roomdatabaseapp.database.UserEntity
import com.njoro.roomdatabaseapp.database.UserViewModel
import com.njoro.roomdatabaseapp.database.UserViewModelFactory
import com.njoro.roomdatabaseapp.user.UserAdapter
import com.njoro.roomdatabaseapp.user.UserApplication


class MainActivity : AppCompatActivity() {

    private val newUserActivityRequestCode = 1

    private val viewModel: UserViewModel by viewModels {
       UserViewModelFactory((application as UserApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val fabDelete = findViewById<FloatingActionButton>(R.id.fab_delete)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewUserActivity::class.java)
            startActivityForResult(intent, newUserActivityRequestCode)

        }

        fabDelete.setOnClickListener {
           deleteDialog()
        }

        viewModel.allUsers.observe(this) { name ->

            // Update the cached copy of the words in the adapter.
                name.let { adapter.submitList(it) }


        }

    }

    private fun deleteDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete all")
        builder.setMessage("Are you sure you want to delete all users")
        builder.setPositiveButton("Yes") { dialog, which ->
            viewModel.deleteAll()
        }
        builder.setNegativeButton("Cancel",null)

        val alertDialog = builder.create()
        alertDialog.show()
    }
}