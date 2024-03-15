package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.ordenarPor
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Centros.Centros_Activity
import com.advantys.brsmovilidaderp.UI.Views.Rutas.Rutas_Activity
import com.advantys.brsmovilidaderp.UI.Views.Series.Series_Activity
import com.advantys.brsmovilidaderp.databinding.ActivityClientesBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Clientes_Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityClientesBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private val clientesViewModel: Cliente_ViewModel by viewModels()
    private var tipoSeleccionado: ordenarPor?= ordenarPor.nombre


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //val toolbar: androidx.appcompat.widget.Toolbar = findViewById((R.id.toolbar_main))
       // setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        //Funcion para que se cierre tocando la pantalla
        //Se le define al navigationView como un rectangulo, con sus coordenadas, cuando el evento de pulsar no pulsa en el rectangulo, se llama a la funcion closeDrawer

        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    val outRect = Rect()
                    navigationView.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                }
            }
            true
        }


        //al implementarlo se quita la funcionalidad de los botones del action bar. Mirar
        clientesViewModel.onCreate()
        clientesViewModel.ClientesModel.observe(this, Observer {
            binding.recyclerviewClientes.layoutManager = LinearLayoutManager(this)
            binding.recyclerviewClientes.adapter = Clientes_Adapter(it, clientesViewModel)
        })


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.centros -> {
                val intent = Intent(this, Centros_Activity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.rutas -> {
                val intent = Intent(this, Rutas_Activity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.series -> {
                val intent = Intent(this, Series_Activity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.acercade -> {
//                val intent= Intent(this, AcercaDe_Activity:class.java)
//                startActivity(intent)
//                finish()
            }

        }
        drawer.closeDrawer(GravityCompat.START)
        return true

    }



    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.ruta->{
                tipoSeleccionado= ordenarPor.ruta
                if(tipoSeleccionado !=null){
                    clientesViewModel.obtenerConsultaClientes(tipoSeleccionado!!)
                }
               true
            }

            R.id.cliente->{
                true
            }

            R.id.nombre->{
                true
            }
            R.id.ordenpersonalizado->{
                true
            }

            else->false
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.popup_menu, menu)
        val menuItem = menu.findItem(R.id.ordenar)
        menuItem?.setOnMenuItemClickListener {
            showPopupMenu()
            true
        }
        return true
    }

    private fun showPopupMenu() {

        val anchorView = findViewById<View>(R.id.ordenar)
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ordenarpor ->{
                    true
                }
                R.id.mostrarclientes ->
                    true

                R.id.restaurarclientes ->
                    true

                R.id.marcardesmarcar ->
                    true

                R.id.desmarcartodos ->
                    true

                else -> {
                    false
                }
            }
        }
        popupMenu.show()
    }

}