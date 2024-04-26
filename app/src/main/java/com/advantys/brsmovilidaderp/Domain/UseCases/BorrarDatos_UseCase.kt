package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.BorrarDatos_Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BorrarDatos_UseCase @Inject constructor(private val repository: BorrarDatos_Repository) {

    suspend fun borrarCobros(fecha: String): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarCobros(fecha)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    suspend fun borrarCargaCero(): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarCargaCero()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun borrarVisitas(): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarVisitas()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun borrarHojaCarga(): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarHojaCarga()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun borrarVentasDetPedidos(fecha: String): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarVentasDetPedidos(fecha)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun borrarVentasCabPedidos(fecha: String): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarVentasCabPedidos(fecha)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun borrarRegistrosSueltos(): Boolean = withContext(Dispatchers.IO) {
        try {
            repository.borrarRegistrosSueltos()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun comprobarDatosPendientesCabPedidos(fecha: String): Int? = withContext(Dispatchers.IO) {
        try {
            repository.comprobarDatosPendientesCabPedidos(fecha)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun comprobarDatosPendientesVisitas(fecha: String): Int? = withContext(Dispatchers.IO) {
        try {
            repository.comprobarDatosPendientesVisitas(fecha)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun comprobarDatosPendientesCobros(fecha: String): Int? = withContext(Dispatchers.IO) {
        try {
            repository.comprobarDatosPendientesCobros(fecha)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun compactarBD(){
      return repository.compactarBD()
    }

}