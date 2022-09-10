package com.neonusa.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inyongtisto.myhelper.extension.pushActivity
import com.neonusa.marketplace.ui.navigation.NavigationActivity
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.RegisterRequest
import com.neonusa.marketplace.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    fun setData() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    fun register(){
        // validation

        if (binding.edtName.text!!.isEmpty()){
            binding.edtName.error = "Nama lengkap tidak boleh kosong"
            return
        }
        if (binding.edtEmail.text!!.isEmpty()){
            binding.edtEmail.error = "Email tidak boleh kosong"
            return
        }
        if (binding.edtPhone.text!!.isEmpty()){
            binding.edtEmail.error = "No. handphone tidak boleh kosong"
            return
        }
        if (binding.edtPassword.text!!.isEmpty()) {
            binding.edtPassword.error = "Password tidak boleh kosong"
            return
        }

        val body = RegisterRequest(
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.register(body).observe(this) {
            when(it.state){
                State.SUCCESS -> {
//                    dismisLoading()
                    Toast.makeText(this, "Selamat datang : ${it.data?.name}", Toast.LENGTH_SHORT).show()
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
//                    dismisLoading()
                    Toast.makeText(this, it.message ?: "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
                State.LOADING -> {
//                    showLoading()
                }
            }

        }
    }
}