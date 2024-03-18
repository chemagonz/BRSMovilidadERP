package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.UI.ViewModels.Centro_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.databinding.ActivityCentrosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Centros_Activity : AppCompatActivity() {
    val centroViewModel: Centro_ViewModel by viewModels()
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
    //Manejo de botones del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //Boton para atras
                val intent = Intent(this, Clientes_Activity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}