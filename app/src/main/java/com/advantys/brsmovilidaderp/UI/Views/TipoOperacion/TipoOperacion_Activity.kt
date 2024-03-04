package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.TipoOperacion_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.TipoOperacion_UseCase
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacionViewModelFactory
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacion_ViewModel

class TipoOperacion_Activity : AppCompatActivity() {

    private val tipoOperacion= TipoOperacion_Dao(this)
    private val tipoOperacionRepository = TipoOperacion_Repository(tipoOperacion)
    private val tipoOperacionUseCase= TipoOperacion_UseCase(tipoOperacionRepository)
    private val tipoOperacionViewModel:  TipoOperacion_ViewModel by viewModels { TipoOperacionViewModelFactory(tipoOperacionUseCase) }
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        //Region variables
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_operacion)

        recyclerView= findViewById<RecyclerView>(R.id.tipoOperacionRecyclerView)

      /* val tipoOperacionList:List<TipoOperacion_Entity?> = tipoOperacion.getAll()
        initRecyclerView(tipoOperacionList)
                                            */
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "TIPOS DE OPERACION"
        }
        tipoOperacionViewModel.onCreate()
        tipoOperacionViewModel.tipoOperacionModel.observe(this, Observer {
            recyclerView.layoutManager= LinearLayoutManager(this)
            recyclerView.adapter= TipoOperacion_Adapter(it, tipoOperacionViewModel)
        })
    }

}