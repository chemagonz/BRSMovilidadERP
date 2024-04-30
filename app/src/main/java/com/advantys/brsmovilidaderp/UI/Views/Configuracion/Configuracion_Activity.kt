package com.advantys.brsmovilidaderp.UI.Views.Configuracion

import android.os.Bundle
import android.transition.TransitionManager
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.databinding.ActivityConfiguracionBinding


class Configuracion_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityConfiguracionBinding
    private val layoutPairs = mutableListOf<Pair<View, View>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLayoutPairs(
            binding.layoutFechas to binding.hiddenLayout,
            binding.layoutExportacion to binding.hiddenlayoutExportacion,
            binding.layoutVentas to binding.hiddenlayoutVentas,
            binding.layoutBusquedas to binding.hiddenLayoutBusqueda,
            binding.layoutBusquedasArt to binding.hiddenLayoutBusquedaArt,
            binding.layoutOrdenArtVentas to binding.hiddenLayoutOrdenArtVentas,
            binding.layoutOrdenArt to binding.hiddenLayoutOrdenArt,
            binding.layoutOtros to binding.hiddenLayoutOtros,
            binding.layoutParametrosImp to binding.hiddenLayoutParametrosImp,
            binding.layoutOpcionesImp to binding.hiddenLayouOpcionesImp
        )

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "CONFIGURACIÓN"
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupLayoutPairs(vararg pairs: Pair<View, View>) {
        layoutPairs.addAll(pairs)
        layoutPairs.forEach { (layout, hiddenLayout) ->
            layout.setOnClickListener {
                val expand = hiddenLayout.visibility != View.VISIBLE
                toggleVisibility(hiddenLayout, expand)
                toggleArrow(expand)
            }
        }
    }

    private fun toggleVisibility(hiddenLayout: View, expand: Boolean) {
        if (expand) {
            hiddenLayout.visibility = View.VISIBLE
        } else {
            TransitionManager.beginDelayedTransition(binding.scrollView)
            binding.scrollView.postDelayed({ hiddenLayout.visibility = View.GONE }, 300)
        }
    }

    private fun toggleArrow(expand: Boolean) {
        val drawableRes = if (expand) R.drawable.expandable_flecha2 else R.drawable.expandable_flecha
        binding.flechaCardview.setImageResource(drawableRes)
    }
}