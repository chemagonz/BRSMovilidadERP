package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.R

class Centros_Activity : AppCompatActivity() {

    //Region variables
    private val centros= Centros_Dao(this)

    //Llamadas a los botones



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centros)

        //Lista para impresion de recyclerView
        val centrosList: List<Centros_Entity?> = centros.getAll()
        initRecyclerView(centrosList)

        //ACTION BAR
        val actionBarColor = ContextCompat.getColor(this, R.color.teal)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CENTROS"
            setBackgroundDrawable(ColorDrawable(actionBarColor))
        }
    }

    private fun initRecyclerView(centrosList: List<Centros_Entity?>){
        val recyclerview= findViewById<RecyclerView>(R.id.centrosRecyclerView)
        recyclerview.layoutManager= LinearLayoutManager(this)
        recyclerview.adapter= Centros_Adapter(centrosList)
    }




}