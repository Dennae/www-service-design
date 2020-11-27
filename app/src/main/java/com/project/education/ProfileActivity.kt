package com.project.education

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileActivity : AppCompatActivity() {

    //UI elements
    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvEmail: TextView? = null
    private var tvEmailVerified: TextView? = null
    private var profileButton: Button? = null

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initialise()
        actionBar()

        // Action Bar back button
        val actionbarCategoryList = supportActionBar
        if (actionbarCategoryList != null) {
            actionbarCategoryList.setDisplayHomeAsUpEnabled(true)
            actionbarCategoryList.title = "Back to Login Page"
        }
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvFirstName = findViewById<View>(R.id.tv_first_name) as TextView
        tvLastName = findViewById<View>(R.id.tv_last_name) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerified = findViewById<View>(R.id.tv_email_verifiied) as TextView
        profileButton = findViewById<View>(R.id.profileButton) as Button
        profileButton!!.setOnClickListener {
            //start next activity
            val intent = Intent(this@ProfileActivity, CategoryListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun actionBar() {
        // Action Bar back button
        val actionbarProfile = supportActionBar
        if (actionbarProfile != null) {
            actionbarProfile.setDisplayHomeAsUpEnabled(true)
            actionbarProfile.title = "Back to Login Page"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        tvEmail!!.text = mUser.email
        tvEmailVerified!!.text = mUser.isEmailVerified.toString()
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvFirstName!!.text = snapshot.child("firstName").value as String
                tvLastName!!.text = snapshot.child("lastName").value as String
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }
}