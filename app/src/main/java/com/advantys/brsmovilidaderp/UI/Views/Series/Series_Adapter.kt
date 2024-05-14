package com.advantys.brsmovilidaderp.UI.Views.Series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemSeriesBinding

class Series_Adapter(private val seriesList:List<Serie?>, private val serieViewModel: Serie_ViewModel): RecyclerView.Adapter<Series_ViewHolder>() {
    private var elementoSeleccionado: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Series_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Series_ViewHolder(layoutInflater.inflate(R.layout.item_series, parent,false))
    }
    override fun getItemCount(): Int = seriesList.size
    override fun onBindViewHolder(holder: Series_ViewHolder, position: Int) {

        val item= seriesList[position]
        holder.binding.nombreSerie.text= item?.nombre
        holder.binding.cSerie.text= item?.cSeries.toString()

        holder.binding.verDetallesButton.setOnClickListener {
            serieViewModel.btnDetalle(item, holder.itemView.context)
        }

        holder.itemView.setOnClickListener {
            val elementoSeleccionadoAnterior = elementoSeleccionado
            elementoSeleccionado= holder.adapterPosition
            notifyItemChanged(elementoSeleccionadoAnterior)
            notifyItemChanged(elementoSeleccionado)
            elementoSeleccionado
        }

        holder.bind( seriesList [position], position==elementoSeleccionado)
    }

    fun getElementoSeleccionado(): Serie? {
        return if(elementoSeleccionado!=-1) seriesList[elementoSeleccionado]
        else null
    }
}
class Series_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemSeriesBinding.bind(view)
    fun bind(seriesModel: Serie?, seleccionado: Boolean){
        binding.nombreSerie.text= seriesModel?.nombre
        binding.cSerie.text= seriesModel?.cSeries.toString()

        if(seleccionado) itemView.setBackgroundColor(itemView.context.getColor(R.color.colorAdvantysApp6))
        else itemView.setBackgroundColor(itemView.context.getColor(android.R.color.white))
    }
}