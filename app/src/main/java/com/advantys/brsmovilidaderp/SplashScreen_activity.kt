package com.advantys.brsmovilidaderp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.databinding.ActivitySplashScreenBinding

class SplashScreen_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    private val SPLASH_TIME: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Modificar versión de la aplicación

        binding.txtVersion.setText("Version 1.0")

        Handler().postDelayed({
            startActivity(Intent( this, Clientes_Activity::class.java))
            finish()
        }, SPLASH_TIME
        )
    }
}