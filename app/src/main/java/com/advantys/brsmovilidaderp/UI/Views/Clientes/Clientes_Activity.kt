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
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.AjustesAvanzados.AjustesAvanzados_Activity
import com.advantys.brsmovilidaderp.UI.Views.Articulos.Articulos_Activity
import com.advantys.brsmovilidaderp.UI.Views.BorrarVentas.BorrarDatos_Activity
import com.advantys.brsmovilidaderp.UI.Views.BuscarClientes.BuscarCliente_Activity
import com.advantys.brsmovilidaderp.UI.Views.Centros.Centros_Activity
import com.advantys.brsmovilidaderp.UI.Views.Configuracion.Configuracion_Activity
import com.advantys.brsmovilidaderp.UI.Views.Exportar.Exportar_Activity
import com.advantys.brsmovilidaderp.UI.Views.Importar.Importar_Activity
import com.advantys.brsmovilidaderp.UI.Views.Rutas.Rutas_Activity
import com.advantys.brsmovilidaderp.UI.Views.Series.Series_Activity
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor
import com.advantys.brsmovilidaderp.Utils.TipoAlerta
import com.advantys.brsmovilidaderp.Utils.mostrar
import com.advantys.brsmovilidaderp.Utils.mostrarSnackbar
import com.advantys.brsmovilidaderp.Utils.orderPor
import com.advantys.brsmovilidaderp.Utils.passwordDialogo
import com.advantys.brsmovilidaderp.databinding.ActivityClientesBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Clientes_Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityClientesBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var adapterCliente: Clientes_Adapter
    private val clientesViewModel: Cliente_ViewModel by viewModels()
    //VARIABLE PARA ACTUALIZAR PANTALLA MEDIANTE REGISTERACTIVITYRESULT

    private val responseLauncher= registerForActivityResult(StartActivityForResult()){ activityResult->

            if(activityResult.resultCode== RESULT_OK){
                clientesViewModel.obtenerConsultaClientes(OrdenarPor.ruta, MostrarPor.todos)
            }
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_BRSMovilidadERP)
        binding = ActivityClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //val toolbar: androidx.appcompat.widget.Toolbar = findViewById((R.id.toolbar_main))
       // setSupportActionBar(toolbar)
        funcionBottomNavigationView()
        binding.bottomNavigationView.background= null
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
        supportActionBar?.title="CLIENTES"

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
        clientesViewModel.obtenerConsultaClientes(OrdenarPor.ruta, MostrarPor.todos)
        clientesViewModel.ClientesModel.observe(this, Observer {
            adapterCliente=  Clientes_Adapter(it, clientesViewModel)
            binding.recyclerviewClientes.layoutManager = LinearLayoutManager(this)
            binding.recyclerviewClientes.adapter = adapterCliente

        })

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.centros -> {
                val intent = Intent(this, Centros_Activity::class.java)
                startActivity(intent)

            }
            R.id.rutas -> {
                val intent = Intent(this, Rutas_Activity::class.java)
                responseLauncher.launch(intent)
            }

            R.id.series -> {
                val intent = Intent(this, Series_Activity::class.java)
                startActivity(intent)

            }
            R.id.borrar_datos->{
                val intent = Intent(this, BorrarDatos_Activity::class.java)
                startActivity(intent)
            }
            R.id.ajustesAvanzados->{
                passwordDialogo("Inserte la contraseña para entrar") { password ->
                    if (clientesViewModel.validarPassword(password)) {
                        val intent = Intent(this, AjustesAvanzados_Activity::class.java)
                        startActivity(intent)
                    } else {
                        mostrarSnackbar( "Contraseña incorrecta", TipoAlerta.error)
                    }
                }
            }
            R.id.acercade -> {
            }
            R.id.articulos->{
                val intent= Intent(this, Articulos_Activity::class.java)
                startActivity(intent)
            }
            R.id.config -> {
                val intent= Intent(this, Configuracion_Activity::class.java)
                startActivity(intent)
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
        when(item.itemId){
            R.id.ruta->{
               orderPor= OrdenarPor.ruta
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.cliente->{
                orderPor= OrdenarPor.cliente
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.nombre->{
              orderPor= OrdenarPor.nombre
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.secuencia->{
               orderPor= OrdenarPor.secuencia
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.ordenpersonalizado->{
                orderPor= OrdenarPor.ordenpersonalizado
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.marcados->{
                mostrar= MostrarPor.marcado
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.desmarcados->{
                mostrar= MostrarPor.desmarcado
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            R.id.todos->{
                mostrar= MostrarPor.todos
                clientesViewModel.obtenerConsultaClientes(orderPor, mostrar)
                return true
            }
            else->false
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_configuracionclientes, menu)
        menuInflater.inflate(R.menu.buscar, menu)
        val menuItem = menu.findItem(R.id.ordenar)
        val busquedaItem= menu.findItem(R.id.busqueda)
        val marcardesmarcarItem= menu.findItem(R.id.marcardesmarcar)
        val desmarcartodosItem= menu.findItem(R.id.desmarcartodos)

        busquedaItem?.setOnMenuItemClickListener {
            val intent= Intent(this, BuscarCliente_Activity::class.java)
            startActivity(intent)
            true
        }
        menuItem?.setOnMenuItemClickListener {
            showPopupMenu()
            true
        }
       marcardesmarcarItem?.setOnMenuItemClickListener {
           val clienteSeleccionado = adapterCliente.getElementoSeleccionado()
           if (clienteSeleccionado != null) {
               clientesViewModel.updateMarcado(clienteSeleccionado.numClientes, !clienteSeleccionado.lmarcado!!, clienteSeleccionado.delegacion)
           } else {
               mostrarSnackbar("No se ha seleccionado ningún cliente", TipoAlerta.informacion)
           }
           true
       }
        desmarcartodosItem?.setOnMenuItemClickListener {
            clientesViewModel.updateDesmarcado()
            true
        }
       return true
    }
    private fun showPopupMenu() {
        val anchorView = findViewById<View>(R.id.ordenar)
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.menuInflater.inflate(R.menu.menu_configuracionclientes, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ordenarpor ->{
                    true
                }
                R.id.mostrarclientes ->{
                    true
                }
                R.id.marcardesmarcar ->{
                    true
                }
                R.id.desmarcartodos ->
                    true
                else -> {
                    false
                }
            }
        }
        popupMenu.show()
    }

    private fun funcionBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Importar -> {
                    startActivity(Intent(this, Importar_Activity::class.java))
                    finish()
                    true
                }
                R.id.Exportar -> {
                    startActivity(Intent(this, Exportar_Activity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}