package com.advantys.brsmovilidaderp.UI.Views.Promociones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.PromoCliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.PromocionesClientes_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemPromocionesClientesBinding

class PromocionesParticulares_Adapter (private val promocionesParList: List<PromoCliente?>, private val promocionParViewModel: PromocionesClientes_ViewModel): RecyclerView.Adapter<PromocionesParticulares_ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionesParticulares_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PromocionesParticulares_ViewHolder(layoutInflater.inflate(R.layout.item_promociones_clientes, parent, false))
    }
    override fun getItemCount(): Int = promocionesParList.size
    override fun onBindViewHolder(holder: PromocionesParticulares_ViewHolder, position: Int) {
        holder.bind(promocionesParList[position])

        val item = promocionesParList[position]
        holder.binding.codigoPromocionPar.text= item?.promocion

        if(item?.descripcion.isNullOrEmpty()){
            holder.binding.descripPromocionPar.text=  "SIN DESCRIPCIÓN"
        }else{
            holder.binding.descripPromocionPar.text= item?.descripcion
        }
        holder.binding.verDetallesButton.setOnClickListener {
            promocionParViewModel.btnDetalle()
        }
    }
}
    class PromocionesParticulares_ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPromocionesClientesBinding.bind(view)
        fun bind(promocionesParModel: PromoCliente?){
            binding.codigoPromocionPar.text = promocionesParModel?.promocion
            binding.descripPromocionPar.text = promocionesParModel?.descripcion
        }
    }