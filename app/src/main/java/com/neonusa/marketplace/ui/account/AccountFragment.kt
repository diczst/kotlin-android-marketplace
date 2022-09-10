package com.neonusa.marketplace.ui.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toGone
import com.neonusa.marketplace.ui.navigation.NavigationActivity
import com.neonusa.marketplace.databinding.FragmentAccountBinding
import com.neonusa.marketplace.ui.mystore.MyStoreActivity
import com.neonusa.marketplace.ui.store.OpenStoreActivity
import com.neonusa.marketplace.ui.updateprofile.UpdateProfileActivity
import com.neonusa.marketplace.util.Constants.USER_URL
import com.neonusa.marketplace.util.Prefs
import com.squareup.picasso.Picasso

class AccountFragment : Fragment() {

    // jika user kembali setelah mengedit data (onbackPressed)
    // update data baru (agar data terupdate seketika kembali dari halaman edit)
    override fun onResume() {
        super.onResume()
        setUser()
    }

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUser()
        mainButton()
        return root
    }

    private fun mainButton(){
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }

        binding.btnUpdate.setOnClickListener {
            intentActivity(UpdateProfileActivity::class.java)
        }

        binding.btnToko.setOnClickListener {
            intentActivity(OpenStoreActivity::class.java)
        }
    }

    private fun setUser(){
        val user = Prefs.getUser()
        if(user != null){
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                tvInitials.text = user.name.getInitial()

                Picasso.get().load(USER_URL + user.image).into(binding.imageProfile)

                if (user.toko != null) {
                    tvStatusToko.toGone()
                    tvNameToko.text = user.toko?.name
                    binding.btnToko.setOnClickListener {
                        intentActivity(MyStoreActivity::class.java)
                    }
                } else {
                    binding.btnToko.setOnClickListener {
                        intentActivity(OpenStoreActivity::class.java)
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}