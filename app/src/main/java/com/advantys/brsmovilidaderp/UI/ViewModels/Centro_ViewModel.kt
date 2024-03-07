

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Centros.DetallesCentro_Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Centro_ViewModel (private val centroUsecase: Centro_UseCase): ViewModel() {

    val centrosModel = MutableLiveData<List<Centro>>()
    val centroModel = Centro()

        fun onCreate(){
        viewModelScope.launch(Dispatchers.Default ) {
            val resultado = centroUsecase()
            if(!resultado.isNullOrEmpty()) centrosModel.postValue(resultado)
        }
    }
        fun onCreateDetalles(){
            viewModelScope.launch(Dispatchers.Default) {
                val resultado = centroUsecase()
                if (resultado != null) {
                  //  centroModel.postValue(resultado)
                }
            }
        }
    fun btnDetalle(item: Centro?,context: Context) {
        val intent = Intent(context,DetallesCentro_Activity::class.java)
        intent.putExtra("numCentro", item?.numCentro)
        context.startActivity(intent)
    }
}
