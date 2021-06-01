package com.kennethmwenda.learnpopote

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class LoginTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.login_tab_fragment, container, false)

        val loginButton = view.findViewById<Button>(R.id.btn_login)
        // set the intent for login button:: this is dashboard activity for debug
        loginButton.setOnClickListener {
            val intent = Intent(requireContext(),DashboardActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}