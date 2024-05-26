package com.jer.ch4_ch5.data.repository.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.databinding.ActivityGateBinding
import com.jer.ch4_ch5.ui.art.ArtViewModel
import com.jer.ch4_ch5.ui.art.HomeArtActivity
import com.jer.ch4_ch5.ui.login.LoginActivity

class GateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGateBinding
    private val viewModel by viewModels<GateViewModel> {
        GateViewModel.provideFactory(this, this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        binding.tvHello.text = "Hello $username"

        binding.btnToHome.setOnClickListener {
            startActivity(Intent(this, HomeArtActivity::class.java))
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}