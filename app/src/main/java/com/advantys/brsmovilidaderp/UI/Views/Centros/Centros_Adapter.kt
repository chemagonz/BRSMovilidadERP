package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.databinding.ItemCentrosBinding


class Centros_Adapter(private val centrosList: List<Centro?>, private val centroViewModel: Centro_ViewModel): RecyclerView.Adapter<Centros_ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Centros_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Centros_ViewHolder(layoutInflater.inflate(R.layout.item_centros, parent, false))
    }

    override fun getItemCount(): Int = centrosList.size

    override fun onBindViewHolder(holder: Centros_ViewHolder, position: Int) {
        holder.bind(centrosList[position])
        val item = centrosList[position]
        holder.binding.nombreCentro.text= item?.nombre
        holder.binding.numCentro.text= item?.numCentro.toString()
        holder.binding.verDetallesButton.setOnClickListener {
                centroViewModel.btnDetalle(item, holder.itemView.context)
        }
    }
}
    class Centros_ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCentrosBinding.bind(view)
        fun bind(centrosModel: Centro?){
            binding.nombreCentro.text= centrosModel?.nombre
            binding.numCentro.text= centrosModel?.numCentro.toString()
        }
    }