package com.app.authentication.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.authentication.R
import com.app.authentication.databinding.ActivityAccountBinding

class AccountActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    lateinit var name: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menu()
        intentData()
        postData()
    }

    private fun menu() {
        binding.accountToolbar.setOnMenuItemClickListener { option ->
            when(option.itemId) {
                R.id.menu_logOut -> {
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun intentData() {
        intent.getStringExtra("name")?.let { name ->
            this.name = name
        }
        intent.getStringExtra("email")?.let { email ->
            this.email = email
        }
    }

    @SuppressLint("SetTextI18n")
    private fun postData() {
        binding.accountWelcomeText.text = getString(R.string.text_welcome) + " " + name
        binding.accountName.text = name
        binding.accountEmail.text = email
    }
}