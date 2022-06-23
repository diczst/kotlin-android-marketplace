package com.neonusa.marketplace.ui.updateprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.setToolbar
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
            register()
        }
    }

    private fun register() {

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

        val body = UpdateProfileRequest(
            1,
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}