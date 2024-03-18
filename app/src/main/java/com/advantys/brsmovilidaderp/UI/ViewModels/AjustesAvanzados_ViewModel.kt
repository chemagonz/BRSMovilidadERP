package com.advantys.brsmovilidaderp.UI.ViewModels

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R

class AjustesAvanzados_ViewModel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes_avanzados_view_model)
    }
}