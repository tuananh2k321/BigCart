package com.example.bigcart.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bigcart.R
import com.example.bigcart.Utilities.Validation
import com.example.bigcart.databinding.ActivityLoginBinding
import com.example.bigcart.model.User
import com.example.bigcart.m_view_model.UserViewModel
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import com.example.bigcart.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding: ActivityLoginBinding
        get() = _binding!!

    // user view model
    private lateinit var userViewModel: UserViewModel

    // firebase
    private lateinit var auth: FirebaseAuth

    // retrofit
    private lateinit var iRetrofit: IRetrofit

    // SharedPreferences
    private lateinit var sharedPrefs: SharedPreferences

    private var hintPassword: Boolean = true

    private val validation = Validation()

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")

    private var isErrorEmail: Boolean = false
    private var isErrorPassword: Boolean = false
    private var isValid: Boolean = false

    private val isFormValid = combine(email, password) { emailValue, passwordValue ->
        isErrorEmail = validation.validateEmail(emailValue)
        isErrorPassword = validation.isEmpty(passwordValue)

        isErrorEmail and isErrorPassword
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // user view model
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // firebase
        auth = FirebaseAuth.getInstance()

        // Initialize SharedPreferences with the application context
        sharedPrefs = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        with(binding) {
            edtEmail.doOnTextChanged { text, start, before, count ->
                email.value = text.toString()
                Log.d("email", email.value)
                isErrorEmail = validation.validateEmail(email.value)
                if (isErrorEmail) {
                    lnlBgEdtEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorEmail.visibility = View.GONE
                } else {
                    lnlBgEdtEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorEmail.visibility = View.VISIBLE
                }
            }

            edtPassword.doOnTextChanged { text, start, before, count ->
                password.value = text.toString()
                Log.d("password", password.value)
                isErrorPassword = validation.isEmpty(password.value)
                if (isErrorPassword) {
                    lnlBgEdtPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                    tvErrorPassword.visibility = View.GONE
                } else {
                    lnlBgEdtPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                    tvErrorPassword.visibility = View.VISIBLE
                }
            }

            btnLogin.setOnClickListener {
                Log.d("btnLogin", "btnLogin")
                if (isValid) {
                    onLoginClick()

                } else {
                    Log.d("btnLogin", "not valid")
                    if (isErrorPassword) {
                        lnlBgEdtPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorPassword.visibility = View.GONE
                    } else {
                        lnlBgEdtPassword.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorPassword.visibility = View.VISIBLE
                    }

                    if (isErrorEmail) {
                        lnlBgEdtEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_edit_text)
                        tvErrorEmail.visibility = View.GONE
                    } else {
                        lnlBgEdtEmail.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_error_edit_text)
                        tvErrorEmail.visibility = View.VISIBLE
                    }


                }
            }

            iconEye.setOnClickListener{
                if (hintPassword) {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT
                    iconEye.setImageResource(R.drawable.icons8_closed_eye_24)
                    hintPassword = !hintPassword
                } else {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    iconEye.setImageResource(R.drawable.icons8_eye_24)
                    hintPassword = !hintPassword
                }
            }

            tvCreateAccount.setOnClickListener {
                val intent = Intent (applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }

            tvForgotPassword.setOnClickListener {
                val intent = Intent (applicationContext, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                isFormValid.collectLatest {
                    Log.d("isFormValid", it.toString())
                    isValid = it
                }
            }
        }
    }

    private fun onLoginClick() {
        val userRequest = User(
            email = email.value,
            password = password.value
        )

        auth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        // Người dùng đã đăng nhập và địa chỉ email đã được xác thực
                        Log.e("registerUserFirebase", "Địa chỉ email đã được xác thực.")
                        iRetrofit.login(userRequest).enqueue(login)
                    } else {
                        // Người dùng đã đăng nhập nhưng địa chỉ email chưa được xác thực
                        Log.e("registerUserFirebase", "Địa chỉ email chưa được xác thực.")
                        // Gửi email xác thực lại
                        binding.tvErrorLogin.text = "Email is not verified. Please check your email for verification."
                        binding.tvErrorLogin.visibility = View.VISIBLE
                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { verificationTask ->
                                if (verificationTask.isSuccessful) {
                                    Log.e("registerUserFirebase", "Gửi email xác thực thành công.")
                                } else {
                                    Log.e("registerUserFirebase", "Gửi email xác thực thất bại.", verificationTask.exception)
                                }
                            }
                    }
                } else {
                    // Đăng nhập thất bại, hiển thị thông báo lỗi nếu cần
                    binding.tvErrorLogin.text = "Login failed. Please check your email and password."
                    binding.tvErrorLogin.visibility = View.VISIBLE
                }
            }
    }


    private val login = object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            val userRes: User? = response.body()
            if (userRes != null) {
                if (userRes.getStatus()) {
                    Log.e("LOGIN", "onResponse: "+ "login success")
                    Log.e("LOGIN", "onResponse:  $userRes.toString()")
                    // Lưu trữ thông tin người dùng vào UserRepository
                    userViewModel.updateUser(userRes)
                    Log.e("userViewModel", userViewModel.getUserLiveData().value?.toString() ?: "null")
                    val editor = sharedPrefs.edit()
                    editor.putString("email", userRes.getEmail())
                    editor.putInt("userId", userRes.getId())
                    editor.apply()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("LOGIN", "onResponse: "+ "login fail")
                    binding.tvErrorLogin.visibility = View.VISIBLE
                }
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.d("LOGIN", "onFailure: ${t.message}")
        }
    }
}