package com.kennethmwenda.learnpopote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fbButton:FloatingActionButton = findViewById(R.id.fab_facebook)
        val googleButton:FloatingActionButton = findViewById(R.id.fab_google)


        val tabLayout : TabLayout = findViewById(R.id.tl_TabLayout1)
        val viewPager:ViewPager = findViewById(R.id.vp_ViewPager1)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.login))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.signUp))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginFragmentAdapter(supportFragmentManager,this,tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        // animate the widgets
        fbButton.animate().translationY(300.0F)
        fbButton.alpha = 0F
        googleButton.animate().translationY(300.0F)
        googleButton.alpha = 0F

        fbButton.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(300).start()
        googleButton.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()
    }
}
