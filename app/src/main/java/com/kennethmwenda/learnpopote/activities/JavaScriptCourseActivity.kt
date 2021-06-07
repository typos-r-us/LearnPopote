package com.kennethmwenda.learnpopote.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YoutubePlayerLP
import com.kennethmwenda.learnpopote.R
import kotlinx.android.synthetic.main.activity_java_script_course.*

class JavaScriptCourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Try to hide system navbar by setting fullscreen activity.
        hideSystemUI()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // End hide system navbar by setting fullscreen activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_script_course)
        val spinner: Spinner = findViewById(R.id.sp_courseTopicName)
        //Create An Array containing topic names
        val topics:MutableList<String> = ArrayList()
        topics.add("") // Blank to allow loading of Intro screen
        topics.add("Introduction to JavaScript") //https://www.youtube.com/watch?v=iHyAxOb0Au4
        topics.add("Review of JavaScript Elements") //https://www.youtube.com/watch?v=Ytcp0zyCUS8
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
                // Set topic header
                tv_topicHeader.text=topics[position]
                // Change the video and notes:
                when (position){
                    0->{/* do nothing*/}
                    1->{
                        ytCreator("iHyAxOb0Au4")
                        tv_topicNotes.text= getString(R.string.javaScriptCourseOutline)
                    }
                    2->{
                        ytCreator("Ytcp0zyCUS8")
                        tv_topicNotes.text=getString(R.string.javaScriptCourseOutline)
                    }
                    3->{ytCreator("s-hdQE61lQg")}
                    4->{ytCreator("u4vDA0Uh0Lw")}
                    5->{ytCreator("q_9u0ipiAro")}
                    6->{ytCreator("S3UKigcH54o")}
                }
                //
            }
        }
    }
    // custom function to load the selected youtube video in ytPlayer
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
                    Toast.makeText(this@JavaScriptCourseActivity,"ERROR INITIATING YOUTUBE", Toast.LENGTH_LONG).show()
                }
            })
        // End custom youtube class and initialization
    }
    fun hideSystemUI(){
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        //or View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE
                )
    }
}
