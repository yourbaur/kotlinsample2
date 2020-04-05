package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        val navbarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navbarTitle
        recyclerView_main.adapter = CourseDetailAdapter()

        //fetchJSON()


    }
    private fun fetchJSON() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY,-1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId
        val request = Request.Builder().url(courseDetailUrl).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                //val courseLesson = gson.fromJson(body, Array<CourseLesson>::class.java)

                //println(homeFeed)
                println(body)
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed by reach destination")
                println(e)

            }


        })
    }
    private class CourseDetailAdapter:RecyclerView.Adapter<CourseLessonViewHolder>() {
        override fun getItemCount(): Int {
            return 5
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val customView  = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

            //val redView = View(parent?.context)
            //redView.minimumHeight =50
            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {

        }

    }
    private class CourseLessonViewHolder(val view: View): RecyclerView.ViewHolder(view)

    {}
}