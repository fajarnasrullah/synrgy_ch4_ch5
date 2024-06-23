package com.jer.ch4_ch5.ui.art

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.ch4_ch5.databinding.ItemArtBinding
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.ArtObject
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.DetailArtResponse

class ArtAdapter(private val context: Context): ListAdapter<
//        DetailArtResponse,
        ArtObject,
        ArtAdapter.ArtViewHolder>(DIFF_CALLBACK) {

    inner class ArtViewHolder(val binding: ItemArtBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(art: ArtObject) {
            binding.titleArtList.text = art.title

            binding.artistList.text = art.principalOrFirstMaker
            binding.yearList.text = art.longTitle
            binding.countryList.text = art.productionPlaces.toString()

            Glide.with(itemView.context)
                .load(art.webImage.url)
                .into(binding.ivList)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val binding = ItemArtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtViewHolder(binding)
    }




    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val art = getItem(position)
        holder.bind(art)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailArtActivity::class.java)
            intent.putExtra("title", art.objectNumber)
            context.startActivity(intent)
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArtObject>() {
            override fun areItemsTheSame(oldItem: ArtObject, newItem: ArtObject): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArtObject, newItem: ArtObject): Boolean {
                return oldItem == newItem
            }

        }


    }

}