package com.neonusa.marketplace.ui.mystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.neonusa.marketplace.R
import com.neonusa.marketplace.databinding.ActivityMyStoreBinding
import com.neonusa.marketplace.ui.alamattoko.StoreAddressActivity
import com.neonusa.marketplace.util.Constants
import com.neonusa.marketplace.util.Prefs
import com.squareup.picasso.Picasso

class MyStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar,"Toko Saya")
        setData()
        setupListener()
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                if (user.toko != null) {
                    tvName.text = user.toko?.name
                    tvInisial.text = user.toko?.name.getInitial()
                    Picasso.get().load(Constants.USER_URL + user.toko?.name)
                        .into(binding.imageProfile)
                }
            }
        }
    }

    private fun setupListener() {
        binding.apply {
            btnAlamat.setOnClickListener {
                intentActivity(StoreAddressActivity::class.java)
            }
        }
    }

    private fun mainButton() {
    }
}