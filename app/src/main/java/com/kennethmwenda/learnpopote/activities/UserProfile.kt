package com.kennethmwenda.learnpopote.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kennethmwenda.learnpopote.R


class UserProfile : AppCompatActivity() {
    var rootRef = FirebaseDatabase.getInstance().reference
    var usersDataRef = rootRef.child("usersData")
    var currentUser = FirebaseAuth.getInstance().currentUser
    var currentUserId = currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
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

                }
                override fun onCancelled(error: DatabaseError) {
                    // Do nothing: TODO("Not yet implemented")
                }
            })
        }else{
            // do something if the user is null
        }


    }
}