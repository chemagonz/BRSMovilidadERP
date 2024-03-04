package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Centro_ViewModel.CentroViewModelFactory

class Centros_Activity : AppCompatActivity() {

    //Region variables
    private val centrosDao= Centros_Dao(this)
    private val centroRepository= Centro_Repository(centrosDao)
    private val centroUseCase = Centro_UseCase(centroRepository)
    private val centroViewModel: Centro_ViewModel by viewModels { CentroViewModelFactory(centroUseCase) }
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centros)

        recyclerView= findViewById<RecyclerView>(R.id.centrosRecyclerView)
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CENTROS"
        }
        centroViewModel.onCreate()
        centroViewModel.centroModel.observe(this, Observer {
            recyclerView.layoutManager= LinearLayoutManager(this)
            recyclerView.adapter = Centros_Adapter(it, centroViewModel)
        })
    }
}