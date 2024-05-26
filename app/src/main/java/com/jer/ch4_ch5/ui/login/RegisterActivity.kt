package com.jer.ch4_ch5.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        RegisterViewModel.provideFactory(this, this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.token.observe(this) {
            Toast.makeText(this, "Kamu Berhasil Registrasi", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            when {
                email.isEmpty() -> binding.edtEmail.error = "Email tidak boleh kosong"
                username.isEmpty() -> binding.edtUsername.error = "Username tidak boleh kosong"
                password.isEmpty() -> binding.edtPassword.error = "Password tidak boleh kosong"

                else -> {
                    viewModel.register(email, username, password)
                }
            }

        }


    }
}