package com.kennethmwenda.learnpopote.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Spinner
import android.widget.*
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YoutubePlayerLP
import com.kennethmwenda.learnpopote.R

class HtmlCourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_course)
        val spinner: Spinner = findViewById(R.id.sp_courseTopicName)
        //Create An Array containing topic names
        val topics:MutableList<String> = ArrayList()
        topics.add("Introduction")
        topics.add("Review of HTML Elements")
        topics.add("Inserting Spaces and Line Breaks")
        topics.add("What is an HTML Table?")
        topics.add("Creating a Hyperlink")
        topics.add("Graphic File Formats")
        //Set Array in Adapter
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, topics)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSelected:String = topics[position]
                val print:String = "$itemSelected selected."
                Toast.makeText(this@HtmlCourseActivity,print,Toast.LENGTH_SHORT).show()

                //custom youtube class and initialization
                val ytPlayerSupportFragment = YoutubePlayerLP.newInstance()
                supportFragmentManager.beginTransaction()
                    .add(R.id.yt_player1, ytPlayerSupportFragment).commit()

                lateinit var videoID: String
                val ytPlayer:YouTubePlayerView = findViewById(R.id.yt_player)
                when(position){
                    0->{
                        videoID="iHyAxOb0Au4"
                        ytPlayer. = videoID
                    }
                }
            }
        }
    }
}