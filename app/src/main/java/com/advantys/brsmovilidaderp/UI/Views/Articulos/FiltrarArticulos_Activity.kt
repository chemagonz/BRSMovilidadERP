package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Familia
import com.advantys.brsmovilidaderp.Domain.Models.Formato
import com.advantys.brsmovilidaderp.Domain.Models.Marca
import com.advantys.brsmovilidaderp.Domain.Models.Sabor
import com.advantys.brsmovilidaderp.Domain.Models.Subfamilia
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Familia_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Formato_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Marca_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Sabor_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Subfamilia_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityFiltrarArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltrarArticulos_Activity : AppCompatActivity() {
    //Instancias ViewModel
    private val articulosviewModel: Articulo_ViewModel by viewModels()
    private  val familiaViewModel: Familia_ViewModel by viewModels()
    private val subfamiliaViewmodel: Subfamilia_ViewModel by viewModels()
    private val formatoViewmodel: Formato_ViewModel by viewModels()
    private val marcaViewmodel: Marca_ViewModel by viewModels()
    private val saborViewmodel: Sabor_ViewModel by viewModels()
    //Instancias Adaptadores

    private lateinit var binding: ActivityFiltrarArticulosBinding

    private var familiaID: Short? = -1
    private var subfamiliaID: Short? = -1
    private var formatoID: Int? = -1
    private var marcaID: String? = null
    private var saborID: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFiltrarArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "FILTRAR"
        }

        //APARTADO FAMILIAS
        familiaViewModel.onCreate()
        familiaViewModel.familiasModel.observe(this, Observer{ familias ->
            binding.autocompleteFamilia.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, familias))
        })
        binding.autocompleteFamilia.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Familia
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.familia}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            familiaID= elementoSeleccionado.familia

        }

        //APARTADO SUBFAMILIAS
        subfamiliaViewmodel.onCreate()
        subfamiliaViewmodel.subfamiliasModel.observe(this, Observer{ subfamilias ->
            binding.autocompleteSubfamilia.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subfamilias))
        })
        binding.autocompleteSubfamilia.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Subfamilia
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.subfamilia}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()

            subfamiliaID= elementoSeleccionado.subfamilia
        }
        //APARTADO FORMATOS
        formatoViewmodel.onCreate()
        formatoViewmodel.formatosModel.observe(this, Observer{ formato ->
            binding.autocompleteFormato.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, formato))
        })
        binding.autocompleteFormato.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Formato
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.formato}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()

            formatoID= elementoSeleccionado.formato
        }
        //APARTADO MARCAS
        marcaViewmodel.onCreate()
        marcaViewmodel.marcasModel.observe(this, Observer{ marca ->
            binding.autocompleteMarca.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, marca))
        })
        binding.autocompleteMarca.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Marca
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.marca}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()

            marcaID= elementoSeleccionado.marca
        }
        //APARTADO SABORES
        saborViewmodel.onCreate()
        saborViewmodel.saboresModel.observe(this, Observer{ sabor ->
            binding.autocompleteSabor.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, sabor))
        })
        binding.autocompleteSabor.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Sabor
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.sabor}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()

            saborID = elementoSeleccionado.sabor
        }
    }

    //Funcion para manejar botones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent()
                intent.putExtra("familia", familiaID)
                intent.putExtra("subfamilia", subfamiliaID)
                intent.putExtra("formato", formatoID)
                intent.putExtra("marca", marcaID)
                intent.putExtra("sabor", saborID)
                //Boton para atras
                setResult(RESULT_OK)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}