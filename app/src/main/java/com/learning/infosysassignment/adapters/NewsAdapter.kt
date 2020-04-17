package com.learning.infosysassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.infosysassignment.R
import com.learning.infosysassignment.model.NewsList

class NewsAdapter(
    val context: Context,
    val newsData: NewsList?
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text_title = view.findViewById<TextView>(R.id.txt_title)
        val txt_title_desc = view.findViewById<TextView>(R.id.txt_news_rec_title_desc)
        val imgview = view.findViewById<ImageView>(R.id.img_news_rec_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.new_list_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {

        return 10

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // bind the data here
        holder.text_title.text = newsData?.rows?.get(position)?.title
        holder.txt_title_desc.text = newsData?.rows?.get(position)?.description
        Glide.with(context).load(newsData?.rows?.get(position)?.imageHref)
            .placeholder(R.drawable.ic_image_black_18dp).into(holder.imgview)

    }

}