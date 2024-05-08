package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.TarifaArticulo
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.TarifaArticulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemTarifaArticuloBinding

class TarifasArticulo_Adapter (private val tarifaArticuloList:List<TarifaArticulo?>, private val tarifaArticuloViewmodel:TarifaArticulo_ViewModel): RecyclerView.Adapter<TarifaArticulo_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifaArticulo_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TarifaArticulo_ViewHolder(layoutInflater.inflate(R.layout.item_tarifa_articulo, parent, false))
    }

    override fun getItemCount(): Int = tarifaArticuloList.size
    override fun onBindViewHolder(holder: TarifaArticulo_ViewHolder, position: Int) {
        holder.bind(tarifaArticuloList[position])

        val item= tarifaArticuloList[position]
        holder.binding.numTarifa.text = item?.numTarifa.toString()
        holder.binding.tarifaPVP.text = item?.pvp.toString()
    }


}

class TarifaArticulo_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemTarifaArticuloBinding.bind(view)
    fun bind(tarifaArticulosModel: TarifaArticulo?){
        binding.numTarifa.text =  tarifaArticulosModel?.numTarifa.toString()
        binding.tarifaPVP.text = tarifaArticulosModel?.pvp.toString()
    }
}
