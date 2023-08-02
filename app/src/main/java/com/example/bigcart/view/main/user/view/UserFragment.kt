package com.example.bigcart.view.main.user.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.databinding.FragmentUserBinding
import com.example.bigcart.m_view_model.UserViewModel
import com.example.bigcart.model.User
import com.example.bigcart.responsitory.UserRepository
import com.example.bigcart.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding

    // share view model
    private lateinit var userViewModel: UserViewModel

    // firebase
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        Log.e("userViewModel", userViewModel.getUserLiveData().value?.toString() ?: "null")

        // Quan sát LiveData từ ViewModel để cập nhật giao diện
        userViewModel.getUserLiveData().observe(viewLifecycleOwner, Observer { user ->
            // Cập nhật giao diện với dữ liệu user
            Log.e("sharedViewModel", user.toString())
            if (user != null) {
                Glide.with(requireContext()).load(user.getAvatar()).fitCenter().into(binding.imgAvatar)
                binding.tvEmail.text = user.getEmail()
                binding.tvName.text = user.getFullName()
            } else {
                binding.imgAvatar.setImageResource(R.drawable.icons8_person_24)
            }
        })

        val imageUrl: String =
            "https://antimatter.vn/wp-content/uploads/2022/10/hinh-anh-gai-xinh-de-thuong.jpg"
        var imgAvatar: ImageView = view.findViewById(R.id.img_avatar)
        Glide.with(requireContext()).load(imageUrl).fitCenter().into(imgAvatar)

        // about me
        var aboutMe: LinearLayout = view.findViewById(R.id.aboutMe)
        aboutMe.setOnClickListener{
            var intent = Intent(requireContext(), AboutMeActivity::class.java)
            startActivity(intent)
        }

        // my address
        var myAddress: LinearLayout = view.findViewById(R.id.myAddress)
        myAddress.setOnClickListener{
            var intent = Intent(requireContext(), MyAddressActivity::class.java)
            startActivity(intent)
        }

        // my order
        var myOrder: LinearLayout = view.findViewById(R.id.myOrder)
        myOrder.setOnClickListener{
            var intent = Intent(requireContext(), MyOrderActivity::class.java)
            startActivity(intent)
        }

        // my card
        var myCard: LinearLayout = view.findViewById(R.id.myCard)
        myCard.setOnClickListener{
            var intent = Intent(requireContext(), MyCardActivity::class.java)
            startActivity(intent)
        }

        // change password
        var changePassword: LinearLayout = view.findViewById(R.id.change_password)
        changePassword.setOnClickListener{
            var intent = Intent(requireContext(), MyCardActivity::class.java)
            startActivity(intent)
        }

        // logout
        var logOut: LinearLayout = view.findViewById(R.id.logout)
        logOut.setOnClickListener{

            auth.signOut()
            var intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            // Kết thúc MainActivity
            requireActivity().finish()
        }

    }

}