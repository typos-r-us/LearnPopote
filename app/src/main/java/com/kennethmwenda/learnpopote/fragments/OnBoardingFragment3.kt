package com.kennethmwenda.learnpopote.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kennethmwenda.learnpopote.R
import com.kennethmwenda.learnpopote.activities.LoginActivity

class OnBoardingFragment3 : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // inflate the view to a variable view1
        val view1: View = inflater.inflate(R.layout.fragment_on_boarding3, container, false)
        // fetch the button id, attach to floatingButton
        val floatingButton: FloatingActionButton = view1.findViewById(R.id.fab_toLogin)
       // Call onClickListener for floating button
        floatingButton.setOnClickListener(this)

        // return the inflated layout for this fragment
        return view1
    }

    companion object {
        fun newInstance(): OnBoardingFragment3 {
            return OnBoardingFragment3()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_toLogin -> {
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags - Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clear past activities
                super.startActivity(intent)
            }
            else -> {
                //do nothing
            }
        }
    }
}