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



        val tabLayout : TabLayout = findViewById(R.id.tl_TabLayout1)
        val viewPager:ViewPager = findViewById(R.id.vp_ViewPager1)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.login))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.signUp))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginFragmentAdapter(supportFragmentManager,this,tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }
}
