package com.advantys.brsmovilidaderp.UI.Views.Centros

import CentroViewModelFactory
import Centro_ViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import com.advantys.brsmovilidaderp.databinding.ActivityCentrosBinding


class Centros_Activity : AppCompatActivity() {
    //Region variables
    private val centrosDao= Centros_Dao(this)
    private val centroRepository= Centro_Repository(centrosDao)
    private val centroUseCase = Centro_UseCase(centroRepository)
    private val centroViewModel: Centro_ViewModel by viewModels { CentroViewModelFactory(centroUseCase) }
    //Binding

    private lateinit var binding: ActivityCentrosBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCentrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CENTROS"
        }
        centroViewModel.onCreate()
        centroViewModel.centrosModel.observe(this, Observer {
            binding.centrosRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.centrosRecyclerView.adapter = Centros_Adapter(it, centroViewModel)
        })
    }
}