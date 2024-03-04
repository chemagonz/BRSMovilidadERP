package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.R

class TipoOperacion_Activity : AppCompatActivity() {

    private val tipoOperacion= TipoOperacion_Dao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        //Region variables
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_operacion)

       val tipoOperacionList:List<TipoOperacion_Entity?> = tipoOperacion.getAll()
        initRecyclerView(tipoOperacionList)

        //ACTION BAR
        val actionBarColor = ContextCompat.getColor(this, R.color.teal)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "TIPOS DE OPERACION"
            setBackgroundDrawable(ColorDrawable(actionBarColor))
        }
    }
    private fun initRecyclerView(tipoOperacionList: List<TipoOperacion_Entity?>) {
        val recyclerView= findViewById<RecyclerView>(R.id.tipoOperacionRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= TipoOperacion_Adapter(tipoOperacionList)
    }
}