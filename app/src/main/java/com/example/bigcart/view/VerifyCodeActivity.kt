package com.example.bigcart.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityVerifyCodeBinding

class VerifyCodeActivity : AppCompatActivity() {
    private var _binding: ActivityVerifyCodeBinding? = null
    private  val binding: ActivityVerifyCodeBinding
        get() = _binding!!


    var resendTime: Long = 60
    var resendEnabled: Boolean = false
    var selectedETPPosition: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // back
            imgBack.setOnClickListener{
                finish()
            }




            edtOtp1.addTextChangedListener(textWatcher)
            edtOtp2.addTextChangedListener(textWatcher)
            edtOtp3.addTextChangedListener(textWatcher)
            edtOtp4.addTextChangedListener(textWatcher)
            edtOtp5.addTextChangedListener(textWatcher)
            edtOtp6.addTextChangedListener(textWatcher)

            // by default open keyboard on first edittext
            showKeyboard(edtOtp1)

            //start countdown timer
            startCountDownTimer(tvResendCode)

            tvResendCode.setOnClickListener{
                if (resendEnabled) {
                    // resend code here

                    startCountDownTimer(tvResendCode)
                }
            }

            btnVerify.setOnClickListener{
                val getOTP: String? = edtOtp1.text.toString()+edtOtp2.text.toString()+edtOtp3.text.toString()+edtOtp4.text.toString()+
                        edtOtp5.text.toString()+edtOtp6.text.toString()

                if (getOTP?.length == 6) {
                    // handle verification process here

                    // start change password activity
                    var intent = Intent(applicationContext, NewPasswordActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            if (p0?.isNotEmpty()!!) {
                when (selectedETPPosition) {
                    1 -> {
                        // select next edit text
                        selectedETPPosition = 2
                        showKeyboard(binding.edtOtp2)
                    }
                    2 -> {
                        // select next edit text
                        selectedETPPosition = 3
                        showKeyboard(binding.edtOtp3)
                    }
                    3 -> {
                        // select next edit text
                        selectedETPPosition = 4
                        showKeyboard(binding.edtOtp4)
                    }
                    4 -> {
                        // select next edit text
                        selectedETPPosition = 5
                        showKeyboard(binding.edtOtp5)
                    }
                    5 -> {
                        // select next edit text
                        selectedETPPosition = 6
                        showKeyboard(binding.edtOtp6)
                    }
                }
            }
        }
    }



    private fun showKeyboard(otpET: EditText): Unit {
        otpET.requestFocus()

        val inputMethodManager: InputMethodManager =
            applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager

        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun startCountDownTimer(tvResendCode: TextView) {
        resendEnabled = false
        val color = ContextCompat.getColor(applicationContext, R.color.detailColor)
        tvResendCode.setTextColor(color)

        object : CountDownTimer(resendTime * 1000, 1000) {
            override fun onTick(p0: Long) {
                tvResendCode.setText("Resend code (" + (p0 / 1000) + ")")
            }

            override fun onFinish() {
                resendEnabled = true
                tvResendCode.setText("Resend code")
                val color = ContextCompat.getColor(applicationContext, R.color.primaryColor)
                tvResendCode.setTextColor(color)
            }
        }.start()
    }

    fun ViewBinding.setOnKeyUpListener(action: (keyCode: Int, event: KeyEvent?) -> Boolean) {
        val onKeyListener = View.OnKeyListener { _, keyCode, event ->
            action(keyCode, event)
        }
        root.setOnKeyListener(onKeyListener)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            when (selectedETPPosition) {
                6 -> {
                    // select previous edit text
                    selectedETPPosition = 5
                    showKeyboard(binding.edtOtp5)
                }
                5 -> {
                    // select previous edit text
                    selectedETPPosition = 4
                    showKeyboard(binding.edtOtp4)
                }
                4 -> {
                    // select previous edit text
                    selectedETPPosition = 3
                    showKeyboard(binding.edtOtp3)
                }
                3 -> {
                    // select previous edit text
                    selectedETPPosition = 2
                    showKeyboard(binding.edtOtp2)
                }
                2 -> {
                    // select previous edit text
                    selectedETPPosition = 1
                    showKeyboard(binding.edtOtp1)
                }
            }
            return true
        } else {
            return super.onKeyUp(keyCode, event)
        }
    }

}