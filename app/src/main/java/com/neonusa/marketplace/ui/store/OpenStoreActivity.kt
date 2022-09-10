package com.neonusa.marketplace.ui.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.*
import com.neonusa.marketplace.core.data.source.model.Toko
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.neonusa.marketplace.databinding.ActivityOpenStoreBinding
import com.neonusa.marketplace.ui.mystore.MyStoreActivity
import com.neonusa.marketplace.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenStoreBinding
    private val viewModel: OpenStoreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainButton()
    }

    private fun mainButton() {
        binding.btnBukToko.setOnClickListener {
            bukaToko()
        }
    }

    private fun bukaToko() {
        val body = CreateTokoRequest(
            userId = Prefs.getUser()?.id ?: 0,
            name = binding.edtName.getString(),
            kota = binding.edtLokasi.getString()
        )
        viewModel.createToko(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    val data = it.data
                    toastSimple("nama Toko:" + data?.name)
                    intentActivity(MyStoreActivity::class.java)

                    val user = Prefs.getUser()
                    user?.toko = Toko(
                        id = data?.id,
                        name = data?.name,
                        kota = data?.kota
                    )
                    Prefs.setUser(user)
                    finish()
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    showLoading()
                }
            }
        }

    }
}