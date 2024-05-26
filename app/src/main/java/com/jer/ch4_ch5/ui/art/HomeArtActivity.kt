package com.jer.ch4_ch5.ui.art

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.databinding.ActivityHomeArtBinding
import com.jer.ch4_ch5.ui.art.response.ArtObject
import com.jer.ch4_ch5.ui.art.response.DetailArtResponse
import com.jer.ch4_ch5.ui.login.DetailUserActivity
import com.jer.ch4_ch5.ui.login.LoginActivity
import com.jer.ch4_ch5.ui.login.RegisterViewModel

class HomeArtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeArtBinding

    private lateinit var viewModel: ArtViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeArtBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArtViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ArtViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvArt.layoutManager = layoutManager



        viewModel.listArt.observe(this) {art ->
            setListData(art)
        }
        viewModel.findAllArts()

//        viewModel.error.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//        }



        binding.ivMiniUser.setOnClickListener {
            startActivity(Intent(this, DetailUserActivity::class.java))
        }


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { v, actionId, event ->
                searchBar.setText(searchView.text)
                searchView.hide()
                Toast.makeText(this@HomeArtActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
//            searchView.editText.addTextChangedListener { editable ->
//                val queryArt = editable.toString()
//                val toInt = queryArt.toInt()
////                viewModel.setSearchQuery(toInt)
//            }
        }

    }


    fun setListData(list: List<ArtObject>) {
        val adapter = ArtAdapter(this)
        adapter.submitList(list)
        binding.rvArt.adapter = adapter
    }
}