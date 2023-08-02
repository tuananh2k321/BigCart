package com.example.bigcart.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.bigcart.R
import com.example.bigcart.Utilities.Validation
import com.example.bigcart.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class ForgotPasswordActivity : AppCompatActivity() {
    private var _bingding: ActivityForgotPasswordBinding? = null
    private val binding: ActivityForgotPasswordBinding
        get() = _bingding!!

    // firebase
    private lateinit var auth: FirebaseAuth

    // validation
    private var validation: Validation = Validation()

    // track value text
    private  var email = MutableStateFlow("")

    // check valid text
    private var isErrorEmail: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bingding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // firebase
        auth = FirebaseAuth.getInstance()



        with(binding){
            // back
            imgBack.setOnClickListener{
                finish()
            }

            // check validate email
            edtEmail.doOnTextChanged { text, start, before, count ->
                email.value = text.toString()
                Log.e("email", email.value )
                isErrorEmail = validation.validateEmail(email.value)
                if (validation.validateEmail(email.value)) {
                    bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorEmail.visibility = View.GONE
                } else {
                    bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorEmail.visibility = View.VISIBLE
                }
            }



            // start verify code activity
            btnRequestCode.setOnClickListener {
                if (isErrorEmail) {

                } else {
                    bgEmail.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorEmail.visibility = View.VISIBLE
                }
            }

            // Get the email link from the intent
            val emailLink = intent.data.toString()

            iconNext.setOnClickListener {
                auth.signInWithEmailLink(email.value!!, emailLink)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.e("sendSignInLinkToEmail2", "success" )

                        } else {
                            // Xử lý lỗi xác minh email
                            val exception = task.exception
                            // ...
                        }
                    }
            }

        }
    }


}