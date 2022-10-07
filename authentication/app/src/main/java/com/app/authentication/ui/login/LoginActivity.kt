package com.app.authentication.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.authentication.R
import com.app.authentication.databinding.ActivityLoginBinding
import com.app.authentication.ui.signup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login()
        authentication()
        signUp()
        forgotPassword()
    }

    private fun authentication() {
        viewModel.authenticationtStatus.observe(this) { status ->

            binding.loginProgressBar.visibility = View.GONE

            if(status) {
                userAccount()
            } else {
                Toast.makeText(this@LoginActivity,
                    getString(R.string.text_toast_error),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun login() {
        binding.loginButton.setOnClickListener {

            binding.loginProgressBar.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                val email = binding.loginEditTextEmail.text.toString()
                val password = binding.loginEditTextPassword.text.toString()

                viewModel.authentication(email, password)
            }, 1000)
        }
    }

    private fun signUp() {
        binding.loginSignUp.setOnClickListener {
            clearFields()
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun userAccount() {
        viewModel.userData.observe(this) { data ->
            clearFields()
            val intent = Intent(this@LoginActivity, AccountActivity::class.java)
            intent.putExtra("name", data?.name)
            intent.putExtra("email", data?.email)
            startActivity(intent)
        }
    }

    private fun forgotPassword() {
        binding.loginForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }

    private fun clearFields() {
        binding.loginEditTextEmail.setText("")
        binding.loginEditTextPassword.setText("")
        binding.loginEditTextEmail.requestFocus()
    }
}