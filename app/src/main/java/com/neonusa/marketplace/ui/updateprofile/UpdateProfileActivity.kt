package com.neonusa.marketplace.ui.updateprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.neonusa.marketplace.databinding.ActivityUpdateProfileBinding
import com.neonusa.marketplace.ui.auth.AuthViewModel
import com.neonusa.marketplace.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.toolbar, "Update Profile")

        mainButton()
        setData()
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
            }
        }
    }

    private fun mainButton() {
        binding.btnSave.setOnClickListener {
            update()
        }
    }

    private fun update() {

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

        val userId = Prefs.getUser()?.id
        val body = UpdateProfileRequest(
            userId ?: 0,
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString()
        )

        viewModel.update(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    // catatan
                    // tidak pake loading, di tutor bang tisto pakai
//                    progress.dismiss()
                    showToast("Selamat datang " + it.data?.name)
                    onBackPressed()
                }
                State.ERROR -> {
//                    progress.dismiss()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
//                    progress.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}