package com.advantys.brsmovilidaderp.UI.Views.Series

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Series_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Serie_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.Serie_UseCase
import com.advantys.brsmovilidaderp.UI.ViewModels.SerieViewModelFactory
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivitySeriesBinding

class Series_Activity : AppCompatActivity() {

    private val seriesDao= Series_Dao(this)
    private val serieRepository= Serie_Repository(seriesDao)
    private val serieUsecase= Serie_UseCase(serieRepository)
    private val seriesViewModel: Serie_ViewModel by viewModels{ SerieViewModelFactory(serieUsecase) }

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
}