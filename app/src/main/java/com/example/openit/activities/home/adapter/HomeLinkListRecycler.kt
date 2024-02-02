package com.example.openit.activities.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openit.R
import com.example.openit.databinding.CardDetailsBinding
import com.example.openit.activities.home.model.Link
import com.example.openit.extensions.militaryToDD_MMM_YYY
import com.example.openit.extensions.truncate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

class HomeLinkListRecycler @Inject constructor(): RecyclerView.Adapter<HomeLinkListRecycler.ViewHolder>() {

    private var dataSet: List<Link> = listOf()
    inner class ViewHolder(val binding: CardDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(dataSet[position]){
                binding.numClick.text = total_clicks.toString();
                binding.linkValueText.text = web_link.truncate(30)
                binding.linkNameText.text = title.truncate(20)
                binding.dateText.text = created_at.militaryToDD_MMM_YYY()
                Glide.with(binding.logoImageView.context)
                    .load(original_image)
                    .placeholder(R.drawable.amazon_icon)
                    .into(binding.logoImageView)
            }
        }
    }

    fun updateDataSet(newDataSet: List<Link>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}