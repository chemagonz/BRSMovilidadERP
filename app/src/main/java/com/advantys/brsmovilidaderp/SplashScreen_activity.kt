package com.advantys.brsmovilidaderp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.databinding.ActivitySplashScreenBinding

class SplashScreen_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    private val SPLASH_TIME: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Modificar versión de la aplicación
        binding.txtVersion.text = "Version 1.0"

        // CountDownTimer para iniciar la actividad después del tiempo de espera
        object : CountDownTimer(SPLASH_TIME, SPLASH_TIME) {
            override fun onTick(millisUntilFinished: Long) {
                // No se usa en este caso
            }
            override fun onFinish() {
                startActivity(Intent(this@SplashScreen_activity, Clientes_Activity::class.java))
                finish()
            }
        }.start()
    }
}