package com.kennethmwenda.learnpopote.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.kennethmwenda.learnpopote.R


class UserProfile : AppCompatActivity() {
    var rootRef = FirebaseDatabase.getInstance().reference
    var userRef = rootRef.child("usersData")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


    }
}