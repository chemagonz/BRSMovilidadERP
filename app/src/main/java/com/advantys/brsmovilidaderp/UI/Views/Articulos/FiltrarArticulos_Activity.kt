package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
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
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Familia_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Formato_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Marca_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Sabor_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Subfamilia_ViewModel
import com.advantys.brsmovilidaderp.Utils.TipoAlerta
import com.advantys.brsmovilidaderp.Utils.mostrarSnackbar
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
    private lateinit var binding: ActivityFiltrarArticulosBinding
    //Instancias Adaptadores

    private var familiaID: Short? = null
    private var subfamiliaID: Short? = null
    private var formatoID: Int? = null
    private var marcaID: String? = null
    private var saborID: String? = null
    //Instancias para guardar item
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_PREFS_KEY = "FiltrarArticulosPrefs"
    private val KEY_SELECTED_FAMILIA = "Familia"
    private val KEY_SELECTED_SUBFAMILIA = "Subfamilia"
    private val KEY_SELECTED_FORMATO = "Formato"
    private val KEY_SELECTED_MARCA = "Marca"
    private val KEY_SELECTED_SABOR = "Sabor"

    private var familiaSeleccionada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFiltrarArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
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
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_FAMILIA, "")
            val position = familias.indexOfFirst { it.nombre == selectedItem }
            if (position != -1) {
                binding.autocompleteFamilia.setText(selectedItem, false)
                familiaID= familias[position].familia
                binding.autocompleteSubfamilia.isEnabled = true
                binding.txtSubfamilia.isEnabled = true
            }
        })
        binding.autocompleteFamilia.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Familia
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.familia}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            familiaID = elementoSeleccionado.familia
            elementoSeleccionado.nombre?.let { saveSelectedItem(it, KEY_SELECTED_FAMILIA) }
            familiaSeleccionada = true
            binding.autocompleteSubfamilia.isEnabled = true
            binding.txtSubfamilia.isEnabled =  true

        }

        //APARTADO SUBFAMILIAS
        subfamiliaViewmodel.onCreate()
        subfamiliaViewmodel.subfamiliasModel.observe(this, Observer{ subfamilias ->
            binding.autocompleteSubfamilia.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subfamilias))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_SUBFAMILIA, "")
            val position = subfamilias.indexOfFirst { it.nombre == selectedItem }
            if (position != -1) {
                binding.autocompleteSubfamilia.setText(selectedItem, false)
                subfamiliaID= subfamilias[position].subfamilia
            }
        })
        binding.txtSubfamilia.isEnabled = false
        binding.autocompleteSubfamilia.isEnabled = false
        binding.autocompleteSubfamilia.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Subfamilia
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.subfamilia}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            subfamiliaID= elementoSeleccionado.subfamilia
            elementoSeleccionado.nombre?.let { saveSelectedItem(it,KEY_SELECTED_SUBFAMILIA) }
        }
        //APARTADO FORMATOS
        formatoViewmodel.onCreate()
        formatoViewmodel.formatosModel.observe(this, Observer{ formato ->
            binding.autocompleteFormato.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, formato))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_FORMATO, "")
            val position = formato.indexOfFirst { it.nombre == selectedItem }
            if (position != -1) {
                binding.autocompleteFormato.setText(selectedItem, false)
                formatoID= formato[position].formato
            }
        })
        binding.autocompleteFormato.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Formato
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.formato}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            formatoID= elementoSeleccionado.formato
            elementoSeleccionado.nombre?.let { saveSelectedItem(it, KEY_SELECTED_FORMATO) }
        }
        //APARTADO MARCAS
        marcaViewmodel.onCreate()
        marcaViewmodel.marcasModel.observe(this, Observer{ marca ->
            binding.autocompleteMarca.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, marca))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_MARCA, "")
            val position = marca.indexOfFirst { it.nombre == selectedItem }
            if (position != -1) {
                binding.autocompleteMarca.setText(selectedItem, false)
                marcaID= marca[position].marca
            }
        })
        binding.autocompleteMarca.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Marca
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.marca}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            marcaID= elementoSeleccionado.marca
            elementoSeleccionado.nombre?.let { saveSelectedItem(it, KEY_SELECTED_MARCA) }
        }
        //APARTADO SABORES
        saborViewmodel.onCreate()
        saborViewmodel.saboresModel.observe(this, Observer{ sabor ->
            binding.autocompleteSabor.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, sabor))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_SABOR, "")
            val position = sabor.indexOfFirst { it.nombre == selectedItem }
            if (position != -1) {
                binding.autocompleteSabor.setText(selectedItem, false)
                saborID= sabor[position].sabor
            }
        })
        binding.autocompleteSabor.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Sabor
            Toast.makeText(
                applicationContext,
                "Código: ${elementoSeleccionado.sabor}, Nombre: ${elementoSeleccionado.nombre}",
                Toast.LENGTH_SHORT
            ).show()
            saborID = elementoSeleccionado.sabor
            elementoSeleccionado.nombre?.let { saveSelectedItem(it, KEY_SELECTED_SABOR) }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.borrar_filtro, menu)
        val borrarItem= menu.findItem(R.id.borrar_filtro)
        borrarItem?.setOnMenuItemClickListener {
            clearSelectedItem()
           true
        }
    return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            binding.autocompleteFamilia.setText("", false)
            binding.autocompleteSubfamilia.setText("", false)
            binding.autocompleteFormato.setText("", false)
            binding.autocompleteMarca.setText("", false)
            binding.autocompleteSabor.setText("", false)
        }
    }
    //Funcion para guardar items en los autocomplete.
    private fun saveSelectedItem(selectedItem: String, key:String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, selectedItem)
        editor.apply()
    }
    //Funcion para borrar items guardados en los autocomplete.
    fun clearSelectedItem(){
        val editor = sharedPreferences.edit()
        editor.remove(KEY_SELECTED_FAMILIA)
        editor.remove(KEY_SELECTED_SUBFAMILIA)
        editor.remove(KEY_SELECTED_FORMATO)
        editor.remove(KEY_SELECTED_MARCA)
        editor.remove(KEY_SELECTED_SABOR)
        editor.apply()
        binding.autocompleteFamilia.setText("", false)
        binding.autocompleteSubfamilia.setText("", false)
        binding.autocompleteFormato.setText("", false)
        binding.autocompleteMarca.setText("", false)
        binding.autocompleteSabor.setText("", false)
        if (binding.autocompleteFamilia.text.isEmpty()) {
            binding.autocompleteSubfamilia.isEnabled = false
            binding.txtSubfamilia.isEnabled = false
        }
       mostrarSnackbar("Filtros borrados correctamente", TipoAlerta.ok)
    }
    //Funcion para manejar botones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if(binding.autocompleteFamilia.text.isEmpty() && binding.autocompleteSubfamilia.text.isEmpty() && binding.autocompleteFormato.text.isEmpty() && binding.autocompleteMarca.text.isEmpty() && binding.autocompleteSabor.text.isEmpty() ) {
                    setResult(RESULT_CANCELED)
                }else{
                    val intent = Intent()
                    intent.putExtra("familia", familiaID)
                    intent.putExtra("subfamilia", subfamiliaID)
                    intent.putExtra("formato", formatoID)
                    intent.putExtra("marca", marcaID)
                    intent.putExtra("sabor", saborID)
                    setResult(RESULT_OK,intent)
                }
                //Boton para atras
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}