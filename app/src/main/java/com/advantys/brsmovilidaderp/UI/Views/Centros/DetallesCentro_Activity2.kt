package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.advantys.brsmovilidaderp.R

class DetallesCentro_Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_centro2)

        //ACTION BAR
        val actionBarColor = ContextCompat.getColor(this, R.color.teal)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            title = "CENTROS"
            setSubtitle("DETALLES")
            setBackgroundDrawable(ColorDrawable(actionBarColor))
        }

    }

}