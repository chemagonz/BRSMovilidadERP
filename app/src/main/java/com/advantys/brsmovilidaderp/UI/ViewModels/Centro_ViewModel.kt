import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import kotlinx.coroutines.launch

class Centro_ViewModel(private val centroUsecase: Centro_UseCase): ViewModel() {

    val centroModel = MutableLiveData<List<Centro>>()

    fun onCreate(){
        viewModelScope.launch {
            val resultado = centroUsecase()
            if(!resultado.isNullOrEmpty()) centroModel.postValue(resultado)
        }
    }

    /* fun btnDetalle(item:Centro, context: Context){
         val intent= Intent(context, DetallesCentro_Activity::class.java)
         context.startActivity(intent)
     }*/


}
