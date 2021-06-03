package com.kennethmwenda.learnpopote

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_tab_fragment.*
import kotlinx.android.synthetic.main.signup_tab_fragment.*

class LoginTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.login_tab_fragment, container, false)

        val loginButton = view.findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            when{ // Check for blank fields, show toast. Trim empty spaces.
                TextUtils.isEmpty(et_loginEmail.text.toString().trim{it <=' '})->{
                    Toast.makeText(
                        requireContext(), "Please enter your email.", Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(et_loginPassword.text.toString().trim{it <=' '})->{
                    Toast.makeText(
                        requireContext(), "Please enter your password.", Toast.LENGTH_LONG
                    ).show()
                }
                else -> { // All fields are entered correctly
                    val userEmail:String = et_loginEmail.text.toString().trim{it <=' '}
                    val userPassword = et_loginPassword.text.toString().trim{it <=' '}
                    // Try to log in the user
                    // create instance of firebase auth and submit reg details
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(
                        OnCompleteListener <AuthResult> {

                            task ->
                            if(task.isSuccessful){
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                // todo: read user data from users node
                                // add the username to the toast
                                Toast.makeText(requireContext(),"Logged in successfully.",Toast.LENGTH_LONG).show()
                                // Do stuff with the newly created user here. NB, once registered, the user is logged in automatically.
                                //
                                val intent = Intent(requireContext(),DashboardActivity::class.java)
                                intent.flags - Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clears all past activities
                                // send over the user details to the dashboard activity
                                // intent.putExtra("userName", userName)
                                // intent.putExtra("userId", FirebaseAuth.getInstance().currentUser!!.uid)
                                // intent.putExtra("emailId", userEmail)
                                // intent.putExtra("phoneNo", userPhone)
                                startActivity(intent)
                                activity?.finish() // clear this activity from history
                            }else {
                                // if the task failed
                                Toast.makeText(
                                    requireContext(),
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }
        }
        return view
    }
}