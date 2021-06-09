package com.kennethmwenda.learnpopote.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.kennethmwenda.learnpopote.R
import kotlinx.android.synthetic.main.activity_user_profile.*


class UserProfile : AppCompatActivity() {
    var rootRef = FirebaseDatabase.getInstance().reference
    var usersDataRef = rootRef.child("usersData")
    var currentUser = FirebaseAuth.getInstance().currentUser
    var currentUserId = currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        // before anything else, make fullscreen
        hideSystemUI()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        // Get current user profile information
        if (currentUserId!=null){
            usersDataRef.child(currentUserId.toString()).addListenerForSingleValueEvent( object:
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // TODO("Not yet implemented")
                    val currentUserName = snapshot.child("userName").value.toString()
                    val currentUserEmail = snapshot.child("userEmail").value.toString()
                    val currentUserPhone = snapshot.child("userPhone").value.toString()

                    // Haya, sasa get the first name using regex and put in the userName tv
                    val fName = currentUserName.split("\\s".toRegex())[0]
                    tv_spinnerIntro.text = fName
                    val prName :TextInputEditText = findViewById(R.id.pr_name)
                    val prEmail :TextInputEditText = findViewById(R.id.pr_email)
                    val prPhone :TextInputEditText = findViewById(R.id.pr_phone)
                    prName.text=currentUserName.toEditable()
                    prEmail.text=currentUserEmail.toEditable()
                    prPhone.text=currentUserPhone.toEditable()
                }
                override fun onCancelled(error: DatabaseError) {
                    // Do nothing: TODO("Not yet implemented")
                }
            })
        }else{
            // do something if the user is null
            Toast.makeText(this,"Current User in null",Toast.LENGTH_LONG).show()
        }

        // Bottom Nav buttons
        val bottomNav : ChipNavigationBar = findViewById(R.id.bottomChip)
        bottomNav.setOnItemSelectedListener { item->
            when(item){
                R.id.mn_logout->{
                    // go to login page, clear all activities, sign out user
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clears all past activities
                    startActivity(intent)
                    finish()
                    FirebaseAuth.getInstance().signOut()
                }
                R.id.mn_home->{
                    // Go to user profile page
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun hideSystemUI(){
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        //or View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE
                )
    }
}