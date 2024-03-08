

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Centros.DetallesCentro_Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Centro_ViewModel (private val centroUsecase: Centro_UseCase): ViewModel() {

    val centrosModel = MutableLiveData<List<Centro>>()
    val centroModel = MutableLiveData<Centro>()

        fun onCreate(){
        viewModelScope.launch(Dispatchers.Default ) {
            val resultado = centroUsecase()
            if(!resultado.isNullOrEmpty()) centrosModel.postValue(resultado)
        }
    }
        fun onCreateDetalles(centro: Int?){
            viewModelScope.launch(Dispatchers.Default) {
                val resultado = centroUsecase(centro)
                if (resultado != null) {
                    centroModel.postValue(resultado)
                }
            }
        }
    fun btnDetalle(item: Centro?,context: Context) {
        val intent = Intent(context,DetallesCentro_Activity::class.java)
        intent.putExtra("numCentro", item?.numCentro)
        context.startActivity(intent)
    }
}


class CentroViewModelFactory (private val centroUseCase: Centro_UseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Centro_ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Centro_ViewModel(centroUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



