package com.neonusa.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.databinding.ActivityLoginBinding
import com.neonusa.marketplace.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    // membuat objek viewmodel
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
        viewModel.text.observe(this, {
            binding.edtEmail.setText(it)
        })

        binding.btnLogin.setOnClickListener {

            // body: LoginRequest
            // akan sama dengan sebuah objek LoginRequest yang memiliki atribut-atribut sbb:

            // - body.email
            // - body.password

            // bentuk json:
            // body {
            //  "email" : "",
            //  "password" = "",
            // }



            val body = LoginRequest(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )

            viewModel.login(body).observe(this, {
            })
        }
    }

    fun testing() {
        val s = Prefs(this)
        if (s.getIsLogin()) {
            binding.tvStatus.text = "SUDAH LOGIN"
            Log.d("RESPON", "PESAN SINGKAT")
        }
    }
}