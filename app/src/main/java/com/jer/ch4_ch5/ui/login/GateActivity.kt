package com.jer.ch4_ch5.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jer.ch4_ch5.MyApplication
import com.jer.ch4_ch5.databinding.ActivityGateBinding
import com.jer.ch4_ch5.ui.art.HomeArtActivity
import com.jer.ch4_ch5.ui.camera.ImageHandlerActivity

class GateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGateBinding
    private val viewModel by viewModels<GateViewModel> {
        (application as MyApplication).viewModelFactory
//        GateViewModel.provideFactory(this, this)
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

        binding.btnToImageHandler.setOnClickListener {
            startActivity(Intent(this, ImageHandlerActivity::class.java))
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