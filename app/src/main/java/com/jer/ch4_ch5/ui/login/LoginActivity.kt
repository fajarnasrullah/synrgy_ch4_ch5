package com.jer.ch4_ch5.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jer.ch4_ch5.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var user: com.jer.ch4_ch5.domain.model.User
    private var isPreferencesEmpty = false
//    val viewModel: LoginViewModel by viewModels()
    private val viewModel by viewModels<LoginViewModel> {
    LoginViewModel.provideFactory(this, this)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            when {
                username.isEmpty() -> binding.edtUsername.error = "Username tidak boleh kosong"
                password.isEmpty() -> binding.edtPassword.error = "Password tidak boleh kosong"
                else -> {
                    viewModel.login(username, password)


//                    saveUser(username)
//                    check(userNote)
                }
            }

        }


        validate()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }


    fun validate() {
        viewModel.works.observe(this) {itsWorks ->
            val username = binding.edtUsername.text.toString().trim()
            if (itsWorks) {
//                startActivity(Intent(this,
////                        HomeNoteActivity::class.java,
//                        HomeArtActivity::class.java)
//                )
                val intent = Intent(this, GateActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)


                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) {message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }





//    fun saveUser(username: String) {
//        val userPreferences = UserPreferences(this)
//        user.username = username
//        userPreferences.setUser(user)

//        if (username == userNote.username) {
//            val intent = Intent(this@LoginActivity, HomeNoteActivity::class.java)
//            startActivity(intent)
//        }
//        else{
//            throw UnsupportedOperationException("user belum registrasi")
////           Log.e("LoginRemote", "user belum tidak tersedia")
//        }
//    }

//    fun check(user: User) {
//        when {
//            user.username.toString().isNotEmpty() -> {
//                binding.tvTextJajal.text = "Ada data nya bre"
//                isPreferencesEmpty = false
//            } else -> {
//            binding.tvTextJajal.text = "belum ada data"
//            isPreferencesEmpty = true
//
//            }
//        }
//    }


}