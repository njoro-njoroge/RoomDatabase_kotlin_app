package com.njoro.roomdatabaseapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.njoro.roomdatabaseapp.database.UserEntity
import com.njoro.roomdatabaseapp.database.UserViewModel
import com.njoro.roomdatabaseapp.database.UserViewModelFactory
import com.njoro.roomdatabaseapp.user.UserApplication

class NewUserActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }
//    private lateinit var edtName: EditText
//    private lateinit var edtEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        val edtUsername = findViewById<EditText>(R.id.edt_name)
        val edtEmailAddress = findViewById<EditText>(R.id.edt_email)
        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {

            val username = edtUsername.text.toString().trim()
            val emailAddress = edtEmailAddress.text.toString().trim()

            if (TextUtils.isEmpty(username)||TextUtils.isEmpty(emailAddress)) {

                Toast.makeText(this,"Please enter username and email address", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(UserEntity(username = username, email = emailAddress))
                Toast.makeText(this,"Added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

}