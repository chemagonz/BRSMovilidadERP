package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.databinding.ActivityCentrosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Centros_Activity : AppCompatActivity() {
    //Region variables
//    private val centrosDao= Centros_Dao(this)
//    private val centroRepository= Centro_Repository(centrosDao)
//    private val centroUseCase = Centro_UseCase(centroRepository)
   private val centroViewModel: Centro_ViewModel by viewModels()
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
        centroViewModel.centroModel.observe(this, Observer {
            binding.centrosRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.centrosRecyclerView.adapter = Centros_Adapter(it, centroViewModel)
        })
    }
}