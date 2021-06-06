package com.kennethmwenda.learnpopote.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
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
import com.google.firebase.database.FirebaseDatabase
import com.kennethmwenda.learnpopote.R
import com.kennethmwenda.learnpopote.models.UserInfoModel
import com.kennethmwenda.learnpopote.activities.DashboardActivity
import kotlinx.android.synthetic.main.signup_tab_fragment.*


class SignUpTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.signup_tab_fragment, container, false)

        val signUpButton = view.findViewById<Button>(R.id.btn_signUp)

        signUpButton.setOnClickListener {
            when { // Check for blank fields, show toast. Trim empty spaces.
                TextUtils.isEmpty(et_loginName.text.toString().trim{it <=' '})->{
                    Toast.makeText(
                        requireContext(), "Please enter your name.",Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(et_signUpEmail.text.toString().trim{it <=' '})->{
                    Toast.makeText(
                        requireContext(), "Please enter your email.",Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(et_signUpPhone.text.toString().trim{it <=' '})->{
                    Toast.makeText(
                        requireContext(), "Please enter your phone number.",Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty((et_signUpPassword1.text.toString().trim{it <=' '}))->{
                    Toast.makeText(
                        requireContext(), "Please enter your password.",Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty((et_signUpPassword2.text.toString().trim{it <=' '}))->{
                    Toast.makeText(
                        requireContext(), "Please enter your password again.",Toast.LENGTH_LONG
                    ).show()
                }
                else -> { // if all fields are entered and passwords match
                    val userName:String = et_loginName.text.toString().trim{it <=' '}
                    val userEmail:String = et_signUpEmail.text.toString().trim{it <=' '}
                    val userPhone = et_signUpPhone.text.toString().trim{it <=' '}
                    val userPassword = et_signUpPassword1.text.toString().trim{it <=' '}


                    // create instance of firebase auth and submit reg details
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(
                            OnCompleteListener <AuthResult>{
                                task->
                                if (task.isSuccessful){
                                    val firebaseUser:FirebaseUser = task.result!!.user!!
                                    //
                                    // Do stuff with the newly created user here. NB, once registered, the user is logged in automatically.
                                    Toast.makeText(requireContext(),"$userName registered successfully.",Toast.LENGTH_LONG).show()
                                    // Get only first name
                                    val fName = userName.split("\\s".toRegex())[0] // credit: https://stackoverflow.com/questions/48379981/split-space-from-string-not-working-in-kotlin#48380101
                                    // Save user info to memory??

                                    val sharedPref = activity?.getSharedPreferences("userMap",Context.MODE_PRIVATE) //val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
                                    val editor = sharedPref?.edit()
                                    editor?.putString("userID", firebaseUser.uid)
                                    editor?.putString("emailId", userEmail)
                                    editor?.putString("phoneNo", userPhone)
                                    editor?.putString("userName", fName)
                                    editor?.commit() // use apply to allow the write to proceed in bg. Commit will write immediately
                                    // End save user info to memory

                                    // Send user to Dashboard
                                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clears all past activities
                                    // send over the user details to the dashboard activity
                                    intent.putExtra("userId", firebaseUser.uid)
                                    intent.putExtra("emailId", userEmail)
                                    intent.putExtra("phoneNo", userPhone)
                                    intent.putExtra("userName", fName)
                                    // Copy user details into users database node
                                    val databaseReference = FirebaseDatabase.getInstance().getReference("usersData")
                                    val dbUserId = databaseReference.push().key.toString()
                                    val dbUser = UserInfoModel(dbUserId,userName,userPhone,userEmail)
                                    databaseReference.child(firebaseUser.uid).setValue(dbUser)
                                    // End Copy user details
                                    startActivity(intent)
                                    activity?.finish() // credit: https://stackoverflow.com/questions/7907900/finishing-current-activity-from-a-fragment
                                } else{ // if the task failed
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