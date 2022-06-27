package com.neonusa.marketplace.ui.updateprofile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.neonusa.marketplace.core.data.source.remote.network.State
import com.neonusa.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.neonusa.marketplace.databinding.ActivityUpdateProfileBinding
import com.neonusa.marketplace.ui.auth.AuthViewModel
import com.neonusa.marketplace.util.Constants
import com.neonusa.marketplace.util.Prefs
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityUpdateProfileBinding

    private var fileImage: File? = null

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
                tvInisial.text = user.name.getInitial()

                Picasso.get().load(Constants.USER_URL + user.image).into(binding.imageProfile)
            }
        }
    }

    private fun mainButton() {
        binding.btnSave.setOnClickListener {
            if (fileImage != null) {
                upload()
            } else {
                update()
            }
        }

        binding.imageProfile.setOnClickListener {
            pickImage()
        }
    }

    private fun pickImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!

            // konversi uri menjadi File
            fileImage = File(uri.path ?: "")

            // Use the uri to load the image
            Picasso.get().load(uri).into(binding.imageProfile)
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

    private fun upload() {
        val idUser = Prefs.getUser()?.id

        // konversi dari tipe File ke Multipart Body
        val file = fileImage.toMultipartBody()

        viewModel.uploadUser(idUser, file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    update()
                }
                State.ERROR -> {
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