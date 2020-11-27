package com.project.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

import android.app.ProgressDialog

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.*

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    //Global variables
    private var email: String? = null
    private var password: String? = null

    //UI elements
    private var loginImage: ImageView? = null
    private var tvForgotPassword: TextView? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressDialog? = null

    //Firebase references
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialise()
        actionBar()
    }

    private fun actionBar() {
        // Action Bar back button
        val actionbarProfile = supportActionBar
        if (actionbarProfile != null) {
            actionbarProfile.title = "Course Project"
        }
    }

    private fun initialise() {
        loginImage = findViewById<View>(R.id.loginBubble) as ImageView
        tvForgotPassword = findViewById<View>(R.id.tv_forgot_password) as TextView
        etEmail = findViewById<View>(R.id.login_email) as EditText
        etPassword = findViewById<View>(R.id.login_password) as EditText
        btnLogin = findViewById<View>(R.id.btn_login) as Button
        btnCreateAccount = findViewById<View>(R.id.btn_register_account) as Button
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        tvForgotPassword!!
            .setOnClickListener { startActivity(Intent(this@LoginActivity,
                ForgotPasswordActivity::class.java)) }
        btnCreateAccount!!
            .setOnClickListener { startActivity(Intent(this@LoginActivity,
                CreateAccountActivity::class.java)) }
        btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mProgressBar!!.setMessage("Registering User...")
            mProgressBar!!.show()
            Log.d(TAG, "Logging in user.")
            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    mProgressBar!!.hide()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


}