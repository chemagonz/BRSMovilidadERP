package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.advantys.brsmovilidaderp.Domain.Models.Familia
import com.advantys.brsmovilidaderp.Domain.Models.Formato
import com.advantys.brsmovilidaderp.Domain.Models.Marca
import com.advantys.brsmovilidaderp.Domain.Models.Sabor
import com.advantys.brsmovilidaderp.Domain.Models.Subfamilia

class Filtros_Adapter {
}
class FamilyAutoComplete_Adapter (context: Context, private val familias: List<Familia?>) :
    ArrayAdapter<Familia?>(context, android.R.layout.simple_dropdown_item_1line, familias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = "${item?.familia} - ${item?.nombre}"
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = familias
                results.count = familias.size
                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}
class subFamilyAutoComplete_Adapter (context: Context, private val subfamilias: List<Subfamilia?>) :
    ArrayAdapter<Subfamilia?>(context, android.R.layout.simple_dropdown_item_1line, subfamilias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = "${item?.subfamilia} - ${item?.nombre}"
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = subfamilias
                results.count = subfamilias.size
                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}
class marcaAutoComplete_Adapter (context: Context, private val marcas: List<Marca?>) :
    ArrayAdapter<Marca?>(context, android.R.layout.simple_dropdown_item_1line, marcas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = "${item?.marca} - ${item?.nombre}"
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = marcas
                results.count = marcas.size
                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}

class formatoAutoComplete_Adapter (context: Context, private val formatos: List<Formato?>) :
    ArrayAdapter<Formato?>(context, android.R.layout.simple_dropdown_item_1line, formatos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = "${item?.formato} - ${item?.nombre}"
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = formatos
                results.count = formatos.size
                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}
class saborAutoComplete_Adapter (context: Context, private val sabores: List<Sabor?>) :
    ArrayAdapter<Sabor?>(context, android.R.layout.simple_dropdown_item_1line, sabores) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = "${item?.sabor} - ${item?.nombre}"
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = sabores
                results.count = sabores.size
                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}
