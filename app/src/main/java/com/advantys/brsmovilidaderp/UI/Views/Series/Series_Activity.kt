package com.advantys.brsmovilidaderp.UI.Views.Series

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivitySeriesBinding

class Series_Activity : AppCompatActivity() {

    private val seriesViewModel: Serie_ViewModel by viewModels()

    private lateinit var binding: ActivitySeriesBinding
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
        seriesViewModel.serieModel.observe(this, Observer{
            binding.seriesRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.seriesRecyclerView.adapter= Series_Adapter(it, seriesViewModel)
        })
    }
}