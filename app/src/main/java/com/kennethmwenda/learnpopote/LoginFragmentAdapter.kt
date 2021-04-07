package com.kennethmwenda.learnpopote

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginFragmentAdapter:FragmentPagerAdapter {
    val  context : Context
    var numTabs: Int= 2

    constructor(fm: FragmentManager, context: Context, numTabs:Int) : super(fm) {
        this.context = context
        this.numTabs = numTabs
    }

    override fun getCount(): Int {
        return numTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> LoginTabFragment()
            1 -> SignUpTabFragment()
            else -> LoginTabFragment() //Should be else -> null
        }
    }
}