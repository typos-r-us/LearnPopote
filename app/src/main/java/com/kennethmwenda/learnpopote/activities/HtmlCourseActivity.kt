package com.kennethmwenda.learnpopote.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Spinner
import android.widget.*
import com.google.android.youtube.player.*
import com.kennethmwenda.learnpopote.R
import kotlinx.android.synthetic.main.activity_html_course.*

class HtmlCourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_course)
        val spinner: Spinner = findViewById(R.id.sp_courseTopicName)
        //Create An Array containing topic names
        val topics:MutableList<String> = ArrayList()
        topics.add("")
        topics.add("Introduction to HTML") //https://www.youtube.com/watch?v=iHyAxOb0Au4
        topics.add("Review of HTML Elements") //https://www.youtube.com/watch?v=Ytcp0zyCUS8
        topics.add("Inserting Spaces and Line Breaks") //https://www.youtube.com/watch?v=s-hdQE61lQg
        topics.add("What is an HTML Table?") //https://www.youtube.com/watch?v=u4vDA0Uh0Lw
        topics.add("Creating a Hyperlink") //https://www.youtube.com/watch?v=q_9u0ipiAro
        topics.add("Graphic File Formats") //https://www.youtube.com/watch?v=S3UKigcH54o
        //Set Array in Adapter
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, topics)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // val itemSelected:String = topics[position]
                // val print:String = "$itemSelected selected."
                // Toast.makeText(this@HtmlCourseActivity,print,Toast.LENGTH_SHORT).show()

                // Set topic in page header
                tv_topicHeader.text=topics[position]
                // Change the video and notes:
                when (position){
                    0->{
                        // do nothing
                    }
                    1->{
                        ytCreator("iHyAxOb0Au4")
                        tv_topicNotes.text= getString(R.string.introToHtml)
                    }
                    2->{
                        ytCreator("Ytcp0zyCUS8")
                    }
                    3->{
                        ytCreator("s-hdQE61lQg")
                    }
                    4->{
                        ytCreator("u4vDA0Uh0Lw")
                    }
                    5->{
                        ytCreator("q_9u0ipiAro")
                    }
                    6->{
                        ytCreator("S3UKigcH54o")
                    }
                }
                //
            }
        }
    }
    fun ytCreator(videoId:String){
        // custom youtube class and initialization
        val ytPlayerSupportFragment = YoutubePlayerLP.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.yt_player, ytPlayerSupportFragment).commit()

        ytPlayerSupportFragment.initialize(
            resources.getString(R.string.apiKey), //IF YOU HAVE NO API KEY IT WONT WORK. But that's actually explained in the docs. So you can google it easily if you don't have one
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo("$videoId") // string has to be https://www.youtube.com/watch?v=----------->9ET6R_MR1Ag<---------
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(this@HtmlCourseActivity,"ERROR INITIATING YOUTUBE",Toast.LENGTH_LONG).show()
                }
            })
        // End custom youtube class and initialization
    }
}