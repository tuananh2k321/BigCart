package com.example.bigcart.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bigcart.NotifyVerifyEmailActivity
import com.example.bigcart.R
import com.example.bigcart.Utilities.Validation
import com.example.bigcart.databinding.ActivityRegisterBinding
import com.example.bigcart.model.User
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import com.example.bigcart.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    // firebase
    private lateinit var auth: FirebaseAuth

    // retrofit
    private lateinit var iRetrofit: IRetrofit

    // gone and visible password
    private var isPassword: Boolean = true
    private var isRePassword: Boolean = true

    // validation
    private var validation = Validation()

    // track value text
    private val email = MutableStateFlow("")
    private val fullName = MutableStateFlow("")
    private val phoneNumber = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val dob = MutableStateFlow("")
    private val rePassword = MutableStateFlow("")

    // check valid text
    private var isErrorEmail: Boolean = false
    private var isErrorFullName: Boolean = false
    private var isErrorPassword: Boolean = false
    private var isErrorPhoneNumber: Boolean = false
    private var isErrorDob: Boolean = false
    private var isErrorRePassword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // firebase
        auth = FirebaseAuth.getInstance()

        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        with(binding) {
            checkbox
            val yourColor = ContextCompat.getColor(applicationContext, R.color.detailColor)
            checkbox.buttonTintList = ColorStateList.valueOf(yourColor)
            checkbox.setTextColor(ContextCompat.getColor(applicationContext, R.color.detailColor))
            checkbox.isChecked = false

            // back
            imgBack.setOnClickListener {
                finish()
            }

            // checkbox
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Log.d("checkbox", checkbox.isChecked.toString())
                    val yourColor = ContextCompat.getColor(applicationContext, R.color.primaryColor)
                    checkbox.buttonTintList = ColorStateList.valueOf(yourColor)
                    checkbox.setTextColor(ContextCompat.getColor(applicationContext, R.color.primaryColor))
                    checkbox.isChecked = true
                } else {
                    Log.d("checkbox", checkbox.isChecked.toString())
                    val yourColor = ContextCompat.getColor(applicationContext, R.color.detailColor)
                    checkbox.buttonTintList = ColorStateList.valueOf(yourColor)
                    checkbox.setTextColor(ContextCompat.getColor(applicationContext, R.color.detailColor))
                    checkbox.isChecked = false
                }
            }

            // validation form
            // Email
            edtEmail.doOnTextChanged { text, start, before, count ->
                email.value = text.toString()
                Log.d("email", email.value)
                isErrorEmail = validation.validateEmail(email.value)
                if (isErrorEmail) {
                    bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorEmail.visibility = View.GONE
                } else {
                    bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorEmail.visibility = View.VISIBLE
                }
            }

            // Full name
            edtFullname.doOnTextChanged { text, start, before, count ->
                fullName.value = text.toString()
                isErrorFullName = validation.validateName(fullName.value)
                if (isErrorFullName) {
                    bgFullName.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorFullname.visibility = View.GONE
                } else {
                    bgFullName.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorFullname.visibility = View.VISIBLE
                }
            }

            // Phone Number
            edtPhone.doOnTextChanged { text, start, before, count ->
                phoneNumber.value = text.toString()
                isErrorPhoneNumber = validation.validateVietnamesePhoneNumber(phoneNumber.value)
                if (isErrorPhoneNumber) {
                    bgPhone.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorPhone.visibility = View.GONE
                } else {
                    bgPhone.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorPhone.visibility = View.VISIBLE
                }
            }

            // Dob
            edtDob.doOnTextChanged { text, start, before, count ->
                dob.value = text.toString()
                isErrorDob = validation.validateBirthday(dob.value)
                if (isErrorDob) {
                    bgDob.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorDob.visibility = View.GONE
                } else {
                    bgDob.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorDob.visibility = View.VISIBLE
                }
            }

            // Password
            edtPassword.doOnTextChanged { text, start, before, count ->
                password.value = text.toString()
                isErrorPassword = validation.validatePassword(password.value)
                if (isErrorPassword) {
                    bgPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorPass.visibility = View.GONE
                } else {
                    bgPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorPass.visibility = View.VISIBLE
                }
            }

            // repeat password
            edtRePassword.doOnTextChanged { text, start, before, count ->
                rePassword.value = text.toString()
                isErrorRePassword = validation.isEmpty(rePassword.value)
                if (isErrorRePassword) {
                    bgRePass.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorRePass.visibility = View.GONE
                } else {
                    bgRePass.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorRePass.visibility = View.VISIBLE
                }
            }

            // gone and visible password
            // password
            imgEyePass.setOnClickListener{
                if (isPassword) {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT
                    imgEyePass.setImageResource(R.drawable.icons8_closed_eye_24)
                    isPassword = !isPassword
                } else {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    imgEyePass.setImageResource(R.drawable.icons8_eye_24)
                    isPassword = !isPassword
                }
            }

            // Re Password
            imgEyeRePass.setOnClickListener{
                if (isRePassword) {
                    edtRePassword.inputType = InputType.TYPE_CLASS_TEXT
                    imgEyeRePass.setImageResource(R.drawable.icons8_closed_eye_24)
                    isRePassword = !isRePassword
                } else {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    imgEyeRePass.setImageResource(R.drawable.icons8_eye_24)
                    isRePassword = !isRePassword
                }
            }


            // Register
            btnSignUp.setOnClickListener{
                if (isErrorEmail && isErrorFullName && isErrorPhoneNumber && isErrorDob
                    && isErrorPassword && isErrorRePassword && checkbox.isChecked) {
                    Log.d("isValidForm", "Valid")
                    if (password.value == rePassword.value) {
                        Log.e("isValidForm", "Valid equal pass and re_pass")
                        onRegisterClick()
                    } else {
                        Log.e("isValidForm", "incorrect equal pass and re_pass")
                        bgRePass.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorRePass.text = "password does not match"
                        tvErrorRePass.visibility = View.VISIBLE
                    }
                } else {
                    Log.e("isValidForm", "InValid")
                    // password
                    if (isErrorPassword) {
                        bgPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorPass.visibility = View.GONE
                    } else {
                        bgPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorPass.visibility = View.VISIBLE
                    }
                    // email
                    if (isErrorEmail) {
                        bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorEmail.visibility = View.GONE
                    } else {
                        bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorEmail.visibility = View.VISIBLE
                    }
                    // full name
                    if (isErrorFullName) {
                        bgFullName.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorFullname.visibility = View.GONE
                    } else {
                        bgFullName.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorFullname.visibility = View.VISIBLE
                    }

                    // phone number
                    if (isErrorPhoneNumber) {
                        bgPhone.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorPhone.visibility = View.GONE
                    } else {
                        bgPhone.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorPhone.visibility = View.VISIBLE
                    }

                    // birthday
                    if (isErrorDob) {
                        bgDob.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorDob.visibility = View.GONE
                    } else {
                        bgDob.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorDob.visibility = View.VISIBLE
                    }

                    // birthday
                    if (isErrorRePassword) {
                        bgRePass.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorRePass.visibility = View.GONE
                    } else {
                        bgRePass.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorRePass.visibility = View.VISIBLE
                    }

                    // checkbox
                    if (checkbox.isChecked) {
                        val yourColor = ContextCompat.getColor(applicationContext, R.color.primaryColor)
                        checkbox.buttonTintList = ColorStateList.valueOf(yourColor)
                        checkbox.setTextColor(ContextCompat.getColor(applicationContext, R.color.primaryColor))
                    } else {
                        val yourColor = ContextCompat.getColor(applicationContext, R.color.errorColor)
                        checkbox.buttonTintList = ColorStateList.valueOf(yourColor)
                        checkbox.setTextColor(ContextCompat.getColor(applicationContext, R.color.errorColor))
                    }
                }
            }
        }

    }

    private fun onRegisterClick() {


        auth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener(this) { task ->
                Log.e("createUserWithEmailAndPassword", email.value)
                Log.e("createUserWithEmailAndPassword", password.value)
                if (task.isSuccessful) {
                    // Đăng ký thành công
                    val user = auth.currentUser
                    Log.e("registerUserFirebase", "Đăng ký thành công, UserID: ${user?.uid}")

                    // Gửi email xác thực
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                Log.e("registerUserFirebase", "Gửi email xác thực thành công.")
                                saveMySql()
                            } else {
                                Log.e("registerUserFirebase", "Gửi email xác thực thất bại.", verificationTask.exception)

                            }
                        }
                } else {
                    // Đăng ký thất bại
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        // Invalid email or password
                    } catch (e: FirebaseAuthInvalidUserException) {
                        // User already exists
                    } catch (e: Exception) {
                        // Handle other exceptions
                    }
                }
            }
    }



    private fun saveMySql() {
        // request
        val userRequest = User(
            fullName = fullName.value,
            password = password.value,
            email = email.value,
            phoneNumber = phoneNumber.value,
            birthday = validation.reverseDateFormat(dob.value)
        )
        Log.e("userRequest", userRequest.toString())
         iRetrofit.register(userRequest).enqueue(register)
    }

    private val register = object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            val userRes: User? = response.body()

            if (userRes != null) {
                if (userRes.getStatus()) {
                    Log.e("REGISTER", "onResponse: "+ "register success")
                    Log.e("REGISTER", "onResponse:  $userRes")
                    var intent = Intent(applicationContext, NotifyVerifyEmailActivity::class.java)
                    startActivity(intent)
                    finish()
                    binding.tvErrorForm.visibility = View.GONE
                } else {
                    Log.e("REGISTER", "STATUS:" + userRes.getStatus())
                    Log.e("REGISTER", "MESSAGE:" + userRes.getMessage())
                    binding.tvErrorForm.visibility = View.VISIBLE
                    binding.tvErrorForm.text = userRes.getMessage()
                    binding.bgEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                }
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.d(">>> register", "onFailure: ${t.message}")
        }
    }
}