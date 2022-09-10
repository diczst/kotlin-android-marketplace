package com.neonusa.marketplace.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showLoading
import com.neonusa.marketplace.ui.navigation.NavigationActivity
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel() // lazy inject

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainButton()
    }

    private fun mainButton(){
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
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


        // it ini mewakili sebuah objek Resource dengan tiga atribut
        // state, message, dan data
        // setelah melakukan pengecekan atribut state kita bisa ambil
        // data atribut messagenya sesuai yang sudah ktia emit di AppRepository

        // state milik objek resource ini tergantung dengan fungsi mana yang kita panggil
        // yang juga tergantung dengan kondisi if mana yang memenuhi di AppRepository

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