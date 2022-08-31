package app.example.mvvm.transporte.features.tramlist.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import app.example.mvvm.transporte.features.tramlist.databinding.ItemTramBinding
import app.example.mvvm.transporte.features.tramlist.domain.model.Tram
import app.example.mvvm.transporte.features.tramlist.presentation.TrolleyItemUI
import app.example.mvvm.transporte.features.tramlist.presentation.toUI
import javax.inject.Inject


@FragmentScoped
class TramListAdapter @Inject constructor(
    @ApplicationContext val context: Context
) : RecyclerView.Adapter<TramListAdapter.ViewHolder>(), Filterable {

    private var list: MutableList<TrolleyItemUI>
    private val rawList: MutableList<TrolleyItemUI>

    var onItemClicked: ((String) -> Unit)? = null

    init {
        list = ArrayList()
        rawList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTramBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun add(itemList: List<Tram>) {
        list.clear()
        rawList.clear()


        itemList.sortedBy { it.title }.forEach {
            add(it)
        }
        notifyDataSetChanged()
    }

    private fun add(tram: Tram) {
        val tramUI = tram.toUI()
        rawList.add(tramUI)
        list.add(tramUI)
    }

    inner class ViewHolder(private val binding: ItemTramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrolleyItemUI) {
            binding.tvName.text = item.title
            binding.tvNumber.text = item.id
            item.destinations?.getOrNull(0)?.direction?.let {
                binding.tvDirection.text = it
                binding.tvDirection.visibility = View.VISIBLE
            } ?: run {
                binding.tvDirection.visibility = View.GONE
            }
            binding.root.setOnClickListener { onItemClicked?.invoke(item.id) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                val itemList = if (charString.isEmpty()) rawList else {
                    val filteredList = ArrayList<TrolleyItemUI>()
                    rawList.filter {
                        constraint?.let { text ->
                            it.title.contains(text, ignoreCase = true) || it.id.contains(text, ignoreCase = true)
                        } ?: false
                    }.forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = itemList }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = if (results?.values == null)
                    rawList
                else
                    results.values as ArrayList<TrolleyItemUI>
                notifyDataSetChanged()
            }
        }
    }
}