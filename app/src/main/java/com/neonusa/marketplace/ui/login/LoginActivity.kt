package com.neonusa.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showLoading
import com.neonusa.marketplace.NavigationActivity
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.databinding.ActivityLoginBinding
import com.neonusa.marketplace.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel:  LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    fun setData() {
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    fun login(){
        // validation
        if (binding.edtEmail.text!!.isEmpty()){
            binding.edtEmail.error = "Email tidak boleh kosong"
            return
        }
        if (binding.edtPassword.text!!.isEmpty()) {
            binding.edtPassword.error = "Password tidak boleh kosong"
            return
        }

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.login(body).observe(this) {
            when(it.state){
                State.SUCCESS -> {
                    dismisLoading()
                    Toast.makeText(this, "Selamat datang : ${it.data?.name}", Toast.LENGTH_SHORT).show()
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    Toast.makeText(this, it.message ?: "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
                State.LOADING -> {
                    showLoading()
                }
            }

        }
    }


}