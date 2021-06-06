package com.kennethmwenda.learnpopote.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kennethmwenda.learnpopote.fragments.OnBoardingFragment1
import com.kennethmwenda.learnpopote.fragments.OnBoardingFragment2
import com.kennethmwenda.learnpopote.fragments.OnBoardingFragment3
import com.kennethmwenda.learnpopote.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // variable for splash screen timeout
        val splashScreenTimeout = 4750L
        var mySharedPreferences:SharedPreferences

        val viewPager = findViewById<ViewPager>(R.id.swipe_pager)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeinanimation)

        //viewPager.adapter(pagerAdapter)
        viewPager.adapter = pagerAdapter

        Timer("",false).schedule(1500){
            img_logo.animate().translationY(2000.0F).setDuration(1000).startDelay = 3000
            txt_appname1.animate().translationY(1800.0F).setDuration(1000).startDelay = 3050
            txt_appname_2.animate().translationY(1400.0F).setDuration(1000).startDelay = 3100
            anim_splash.animate().translationY(1400.0F).setDuration(1000).startDelay = 3150
            txt_footnote.animate().translationY(1400.0F).setDuration(1000).startDelay = 3200
            img_bg.animate().translationY(-2000.0F).setDuration(1000).startDelay = 3250
        }

        viewPager.startAnimation(fadeInAnimation)

        // Use shared preferences to decide whether to show onboarding slides or not
        Handler().postDelayed(Runnable {
            kotlin.run {
                mySharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE)
                val isFirstUse:Boolean = mySharedPreferences.getBoolean("firstUse", true)

                if (isFirstUse){
                    val editor = mySharedPreferences.edit().putBoolean("firstUse",false)
                    editor.apply() // removed commit() for performance consideration. apply() can delay the write
                }else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, splashScreenTimeout)

    }
    class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        private val numFragments:Int = 3
        override fun getItem(position: Int): Fragment {
            return when (position){
                0 -> OnBoardingFragment1()
                1 -> OnBoardingFragment2()
                2 -> OnBoardingFragment3()
                else -> OnBoardingFragment1() //Should be else -> null
            }
        }
        override fun getCount(): Int {
            return numFragments
        }

    }
}