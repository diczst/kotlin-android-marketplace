package com.neonusa.marketplace

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.neonusa.marketplace.databinding.ActivityNavigationBinding
import com.neonusa.marketplace.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)

        // persiapan untuk mengecek menu mana yang ditekan oleh user
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_cart
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // mengecek menu yang ditekan oleh user
        navView.setOnItemSelectedListener {
            if(it.itemId == R.id.navigation_cart){
                val sharedPrefs = Prefs(this)
                if(sharedPrefs.getIsLogin()){
                    Log.d("TAG", "Sudah login")
                } else {
                    Log.d("TAG", "belum login, pindah ke halaman login")
                }
            } else {
                Log.d("TAG", "halaman lain : " + it.title)
            }

            return@setOnItemSelectedListener true
        }
    }
}