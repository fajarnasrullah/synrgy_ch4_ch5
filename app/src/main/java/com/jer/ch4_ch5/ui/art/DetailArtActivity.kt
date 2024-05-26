package com.jer.ch4_ch5.ui.art

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.databinding.ActivityDetailArtBinding

class DetailArtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtBinding
    private lateinit var viewModel: ArtViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ArtViewModel::class.java)

        val id = intent.getStringExtra("title") ?: ""
//        val id = "SK-C-5"

        viewModel.findDetailArt(id)
        viewModel.detailArt.observe(this) {detailArt ->
            binding.titleArtDetail.text = detailArt.title
            binding.artistDetail.text = "By ${detailArt.principalMaker}"
            binding.descDetail.text = detailArt.description
            binding.departmentDetail.text = detailArt.objectTypes.toString()
            binding.countryDetail.text = detailArt.productionPlaces.toString()

            Glide.with(this)
                .load(detailArt.webImage.url)
                .into(binding.ivDetail)

        }
    }
}
