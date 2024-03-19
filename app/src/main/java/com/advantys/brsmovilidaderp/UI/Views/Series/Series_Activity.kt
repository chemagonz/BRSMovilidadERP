package com.advantys.brsmovilidaderp.UI.Views.Series

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivitySeriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Series_Activity : AppCompatActivity() {

//    private val seriesDao= Series_Dao(this)
//    private val serieRepository= Serie_Repository(seriesDao)
//    private val serieUsecase= Serie_UseCase(serieRepository)
     val seriesViewModel: Serie_ViewModel by viewModels()
//    { SerieViewModelFactory(serieUsecase) }

    private lateinit var binding: ActivitySeriesBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
        }
        seriesViewModel.onCreate()
        seriesViewModel.seriesModel.observe(this, Observer{
            binding.seriesRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.seriesRecyclerView.adapter= Series_Adapter(it, seriesViewModel)
        })
    }
    //Manejo de botones del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //Boton para atras
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}