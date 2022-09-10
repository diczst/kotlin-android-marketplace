package com.neonusa.marketplace.ui.alamattoko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toVisible
import com.neonusa.marketplace.adapter.StoreAddressAdapter
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.databinding.ActivityStoreAdressBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class StoreAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreAdressBinding
    private val viewModel: StoreAddressViewModel by viewModel()
    private var adapter = StoreAddressAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        getData()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.tvError.toGone()
                    val data = it.data ?: emptyList()
                    Log.i("StoreAddressActivity", "getData: $data")
                    adapter.addItems(data)

                    if (data.isEmpty()) {
                        binding.tvError.toVisible()
                    }
                }
                State.ERROR -> {
                    binding.tvError.toVisible()
                    Log.i("StoreAddressActivity", "getData: ${it.message}")

                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun mainButton() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}