package com.app.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.app.authentication.databinding.ActivityMainBinding
import com.app.authentication.ui.login.LoginActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen()
    }

    private fun splashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}