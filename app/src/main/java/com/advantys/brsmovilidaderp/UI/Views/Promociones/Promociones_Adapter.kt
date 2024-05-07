package com.advantys.brsmovilidaderp.UI.Views.Promociones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Promocion
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Promociones_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemPromocionesGeneralesBinding

class Promociones_Adapter (private val promocionesList: List<Promocion?>, private val promocionViewModel: Promociones_ViewModel): RecyclerView.Adapter<Promociones_ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Promociones_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Promociones_ViewHolder(layoutInflater.inflate(R.layout.item_promociones_generales, parent, false))
    }
    override fun getItemCount(): Int = promocionesList.size
    override fun onBindViewHolder(holder: Promociones_ViewHolder, position: Int) {
        holder.bind(promocionesList[position])

        val item = promocionesList[position]
        holder.binding.codigoPromocion.text= item?.promocion

        if(item?.descripcion.isNullOrEmpty()){
            holder.binding.descripPromocion.text= "SIN DESCRIPCIÓN"
        }else{
            holder.binding.descripPromocion.text= item?.descripcion
        }
        holder.binding.verDetallesButton.setOnClickListener {
            promocionViewModel.btnDetalle()
        }
    }
}
    class Promociones_ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPromocionesGeneralesBinding.bind(view)
        fun bind(promocionesModel: Promocion?){
            binding.codigoPromocion.text = promocionesModel?.promocion
            binding.descripPromocion.text = promocionesModel?.descripcion
        }
    }