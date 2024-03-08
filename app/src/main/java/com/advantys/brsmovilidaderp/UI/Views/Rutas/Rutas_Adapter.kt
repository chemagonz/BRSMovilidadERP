package com.advantys.brsmovilidaderp.UI.Views.Rutas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Ruta_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemRutasBinding

class Rutas_Adapter (private val rutaList: List<Ruta?>, private val rutaViewModel: Ruta_ViewModel) : RecyclerView.Adapter<Rutas_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rutas_ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return Rutas_ViewHolder(layoutInflater.inflate(R.layout.item_rutas, parent,false))
    }

    override fun getItemCount(): Int = rutaList.size
    override fun onBindViewHolder(holder: Rutas_ViewHolder, position: Int) {
        holder.bind(rutaList[position])
        val item = rutaList[position]
        //Agregar item_rutas 07/03/2024

        holder.binding.nombreRuta.text= item?.nombre
        holder.binding.cRuta.text= item?.numRuta.toString()
        holder.binding.check.isChecked= item?.lmarcado==true

    }
}
class Rutas_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemRutasBinding.bind(view)

    fun bind(rutasModel:Ruta?){
        binding.nombreRuta.text= rutasModel?.nombre
        binding.cRuta.text= rutasModel?.numRuta.toString()
    }
}