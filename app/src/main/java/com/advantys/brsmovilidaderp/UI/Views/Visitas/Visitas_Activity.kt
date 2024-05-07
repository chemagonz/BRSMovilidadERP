package com.advantys.brsmovilidaderp.UI.Views.Visitas

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityVisitasBinding

class Visitas_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityVisitasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVisitasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "VISITAS"
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}