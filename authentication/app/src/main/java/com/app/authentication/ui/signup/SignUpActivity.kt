package com.app.authentication.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.authentication.R
import com.app.authentication.databinding.ActivitySignUpBinding
import com.app.authentication.databinding.DialogErrorBinding
import com.app.authentication.databinding.DialogSuccessBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cancel()
        getData()
        invalidField()
        insertStatus()
    }

    private fun cancel() {
        binding.signupBack.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        binding.signupButton.setOnClickListener {
            val name = binding.signupEditTextName.text.toString()
            val email = binding.signupEditTextEmail.text.toString()
            val password = binding.signupEditTextPassword.text.toString()

            viewModel.getData(name, email, password)
        }
    }

    private fun invalidField() {
        viewModel.invalidField.observe(this) {
            showDialogError()
        }
    }

    private fun insertStatus() {
        viewModel.insertStatus.observe(this) { success ->
            if(success) {
                showDialogSuccess()
            } else {
                showDialogError()
            }
        }
    }

    private fun showDialogError() {
        val dialog: AlertDialog
        val view = DialogErrorBinding.inflate(layoutInflater)
        val build = AlertDialog.Builder(this, R.style.AlertDialog)

        build.setView(view.root)
        dialog = build.create()
        dialog.show()

        view.dialogErrorButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDialogSuccess() {
        val dialog: AlertDialog
        val view = DialogSuccessBinding.inflate(layoutInflater)
        val build = AlertDialog.Builder(this, R.style.AlertDialog)

        build.setView(view.root)
        dialog = build.create()
        dialog.show()

        view.dialogSuccessButton.setOnClickListener {
            dialog.dismiss()
        }
    }
}