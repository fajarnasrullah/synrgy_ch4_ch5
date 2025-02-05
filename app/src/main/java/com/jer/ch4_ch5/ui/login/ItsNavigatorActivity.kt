package com.jer.ch4_ch5.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jer.ch4_ch5.MyApplication

class ItsNavigatorActivity: AppCompatActivity() {

    private val viewModel by viewModels<ItsNavigatorViewmodel>{
        (application as MyApplication).viewModelFactory
//        ItsNavigatorViewmodel.provideFactory(this, this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLogin.observe(this) {isLogin ->
            if (isLogin) {
                startActivity(Intent(this, GateActivity::class.java))
            } else startActivity(Intent(this, LoginActivity::class.java))

        }

        viewModel.loginCheck()

    }

}