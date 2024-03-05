import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Centro_ViewModel @Inject constructor(private val centroUsecase: Centro_UseCase): ViewModel() {

    val centroModel = MutableLiveData<List<Centro>>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default ) {
            val resultado = centroUsecase()
            if(!resultado.isNullOrEmpty()) centroModel.postValue(resultado)
        }
    }

    /* fun btnDetalle(item:Centro, context: Context){
         val intent= Intent(context, DetallesCentro_Activity::class.java)
         context.startActivity(intent)
     }*/


}
