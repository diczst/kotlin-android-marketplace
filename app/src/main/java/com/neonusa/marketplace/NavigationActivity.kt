package com.neonusa.marketplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.neonusa.marketplace.databinding.ActivityNavigationBinding
import com.neonusa.marketplace.ui.auth.LoginActivity
import com.neonusa.marketplace.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)

        // ini dikomentari agar bottomnav tidak berhubungan lagi dengan actionbar yang telah dihilangkan
        // pada values/themes.xml
//        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        // mengecek menu yang ditekan oleh user
        // mengoverride setOnItemSelectedListener berdampak pada fragment yang tidak bisa
        // berpindah, oleh sebab itu diperlukan navController.navigate(it.itemId)

        navView.setOnItemSelectedListener {
            if(it.itemId == R.id.navigation_cart){
                if(Prefs.isLogin){
                    Log.d("TAG", "Sudah login")
                    navController.navigate(it.itemId)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "belum login, pindah ke halaman login")

                    // agar animasi clicked icon tidak muncul
                    return@setOnItemSelectedListener false
                }
            } else if (it.itemId == R.id.navigation_account){
                if(Prefs.isLogin){
                    Log.d("TAG", "Sudah login")
                    navController.navigate(it.itemId)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "belum login, pindah ke halaman login")
                    // agar animasi clicked icon tidak muncul
                    return@setOnItemSelectedListener false
                }
            }
            else {
                navController.navigate(it.itemId)
                Log.d("TAG", "halaman lain : " + it.title)
            }

            return@setOnItemSelectedListener true
        }
    }
}