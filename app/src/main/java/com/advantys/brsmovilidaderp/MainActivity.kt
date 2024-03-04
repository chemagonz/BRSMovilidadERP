package com.advantys.brsmovilidaderp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao

class MainActivity : AppCompatActivity() {

    //Region variables
    private val tipoOperacion= TipoOperacion_Dao(this)
    private val centros= Centros_Dao(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //val tipoOperacionList: List<TipoOperacion_Entity> = tipoOperacion.getAll()
        //initRecyclerView(tipoOperacionList)

    }


}