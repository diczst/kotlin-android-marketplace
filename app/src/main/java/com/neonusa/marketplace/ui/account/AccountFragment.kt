package com.neonusa.marketplace.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.pushActivity
import com.neonusa.marketplace.NavigationActivity
import com.neonusa.marketplace.core.data.source.model.User
import com.neonusa.marketplace.databinding.FragmentAccountBinding
import com.neonusa.marketplace.util.Prefs

class AccountFragment : Fragment() {

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
    }

    private fun setUser(){
        val user = Prefs.getUser()
        if(user != null){
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone

                tvInitials.text = user.name.getInitial()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}