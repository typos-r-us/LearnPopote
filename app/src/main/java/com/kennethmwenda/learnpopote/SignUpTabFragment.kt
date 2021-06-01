package com.kennethmwenda.learnpopote

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SignUpTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.signup_tab_fragment, container, false)

        val signUpButton = view.findViewById<Button>(R.id.btn_signUp)
        // set the intent for signUpButton :: this is dashboard activity for debug
        signUpButton.setOnClickListener {
            val intent = Intent(requireContext(),DashboardActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}