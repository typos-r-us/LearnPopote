package com.kennethmwenda.learnpopote

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_on_boarding3.*
import java.util.*
import kotlin.concurrent.schedule

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnBoardingFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnBoardingFragment3 : Fragment(), View.OnClickListener {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

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
////////
    companion object {
        fun newInstance(): OnBoardingFragment3 {
            return OnBoardingFragment3()
        }
    }
////////
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_toLogin -> {
                val intent = Intent(activity,LoginActivity::class.java)
                super.startActivity(intent)
            }
            else -> {
                //do nothing
            }
        }
    }
///////



//    private fun goToLogin(){
//
//    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment OnBoardingFragment3.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                OnBoardingFragment3().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
//    }
}