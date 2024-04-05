package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
    private lateinit var familiaAdapter: FamilyAutoComplete_Adapter
    private lateinit var subfamiliaAdapter: subFamilyAutoComplete_Adapter
    private lateinit var formatoAdapter: formatoAutoComplete_Adapter
    private lateinit var marcaAdapter: marcaAutoComplete_Adapter
    private lateinit var saborAdapter: saborAutoComplete_Adapter

    private lateinit var binding: ActivityFiltrarArticulosBinding
    private var familiaSeleccionada: Boolean = false


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
        familiaAdapter= FamilyAutoComplete_Adapter(this, mutableListOf(), articulosviewModel)
        binding.autocompleteFamilia.setAdapter(familiaAdapter)
        familiaViewModel.familiasModel.observe(this, Observer{ familias ->
            familiaAdapter.clear()
            familiaAdapter.addAll(familias)
        })
        binding.autocompleteFamilia.setOnItemClickListener { _, _, position, _ ->
            familiaSeleccionada= true
            binding.autocompleteSubfamilia.isEnabled = true
            val selectedFamilia = familiaAdapter.getItem(position)
            binding.txtFamilia.editText?.setText("${selectedFamilia?.familia} - ${selectedFamilia?.nombre}")

        }

        //APARTADO SUBFAMILIAS
        binding.autocompleteSubfamilia.isEnabled = false
        subfamiliaViewmodel.onCreate()
        subfamiliaAdapter= subFamilyAutoComplete_Adapter(this, mutableListOf(),articulosviewModel)
        binding.autocompleteSubfamilia.setAdapter(subfamiliaAdapter)
        subfamiliaViewmodel.subfamiliasModel.observe(this, Observer{ subfamilias ->
            subfamiliaAdapter.clear()
            subfamiliaAdapter.addAll(subfamilias)
        })
        binding.autocompleteSubfamilia.setOnItemClickListener { _, _, position, _->
            val selectedSubfamilia= subfamiliaAdapter.getItem(position)
            binding.txtSubfamilia.editText?.setText("${selectedSubfamilia?.subfamilia} - ${selectedSubfamilia?.nombre}")

        }
        //APARTADO FORMATOS
        formatoViewmodel.onCreate()
        formatoAdapter= formatoAutoComplete_Adapter(this, mutableListOf(),articulosviewModel)
        binding.autocompleteFormato.setAdapter(formatoAdapter)
        formatoViewmodel.formatosModel.observe(this, Observer{ formato ->
            formatoAdapter.clear()
            formatoAdapter.addAll(formato)
        })
        binding.autocompleteFormato.setOnItemClickListener { _, _, position, _->
            val selectedFormato= formatoAdapter.getItem(position)
            binding.txtFormato.editText?.setText("${selectedFormato?.formato} - ${selectedFormato?.nombre}")
        }
        //APARTADO MARCAS
        marcaViewmodel.onCreate()
        marcaAdapter= marcaAutoComplete_Adapter(this, mutableListOf(),articulosviewModel)
        binding.autocompleteMarca.setAdapter(marcaAdapter)
        marcaViewmodel.marcasModel.observe(this, Observer{ marca ->
            marcaAdapter.clear()
            marcaAdapter.addAll(marca)
        })
        binding.autocompleteMarca.setOnItemClickListener { _, _, position, _->
            val selectedMarca= marcaAdapter.getItem(position)
            binding.txtMarca.editText?.setText("${selectedMarca?.marca} - ${selectedMarca?.nombre}")
        }
        //APARTADO SABORES
        saborViewmodel.onCreate()
        saborAdapter= saborAutoComplete_Adapter(this, mutableListOf(),articulosviewModel)
        binding.autocompleteSabor.setAdapter(saborAdapter)
        saborViewmodel.saboresModel.observe(this, Observer{ sabor ->
            saborAdapter.clear()
            saborAdapter.addAll(sabor)
        })
        binding.autocompleteSabor.setOnItemClickListener { _, _, position, _->
            val selectedSabor= saborAdapter.getItem(position)
            binding.txtSabor.editText?.setText("${selectedSabor?.sabor} - ${selectedSabor?.nombre}")
        }
    }

    //Funcion para manejar botones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //Boton para atras
                setResult(RESULT_OK)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}