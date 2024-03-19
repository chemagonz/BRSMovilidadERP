package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacion_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityTipoOperacionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TipoOperacion_Activity : AppCompatActivity() {
    val tipoOperacionViewModel: TipoOperacion_ViewModel by viewModels()
    private lateinit var binding : ActivityTipoOperacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTipoOperacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "TIPOS DE OPERACION"
        }
        tipoOperacionViewModel.onCreate()
        tipoOperacionViewModel.tipoOperacionModel.observe(this, Observer {
            binding.tipoOperacionRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.tipoOperacionRecyclerView.adapter= TipoOperacion_Adapter(it, tipoOperacionViewModel)
        })
    }
}