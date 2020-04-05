package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){
    val videoTitles = listOf<String>("First Title","2nd","3rd")
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row,parent,false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        holder.view.textView_video_title.text = video.name
        holder.view.channel_name.text = video.channel.name
        val thumbnailImageView = holder.view.imageView_pic
        Picasso.with(holder.view.context).load(video.imageUrl).into(thumbnailImageView)
        val channelProfileImageView = holder.view.imageView_channel_profile
        Picasso.with(holder.view.context).load(video.imageUrl).into(channelProfileImageView)

        holder?.video = video
    }


}
class CustomViewHolder(val view: View, var video:Video?=null):RecyclerView.ViewHolder(view) {
    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"
    }
    init {
        view.setOnClickListener {
            println("TEEEEEEEEEEEEEEEEEEEEEEEST")
            val intent = Intent(view.context, CourseDetailActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            view.context.startActivity(intent)
        }
    }

}