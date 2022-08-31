package app.example.mvvm.transporte.features.tram.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import app.example.mvvm.transporte.features.tram.databinding.ItemTramStopBinding
import app.example.mvvm.transporte.features.tram.domain.model.TramDestination
import javax.inject.Inject
import app.example.mvvm.transporte.common.resources.R as CR

@FragmentScoped
class TramAdapter @Inject constructor(
    @ApplicationContext val context: Context
) : RecyclerView.Adapter<TramAdapter.ViewHolder>() {

    private val list: MutableList<TramDestination>

    init {
        list = ArrayList()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTramStopBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearAll() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addAll(times: List<TramDestination>?) {
        times?.let {
            list.clear()
            list.addAll(it)
            notifyItemRangeChanged(0, list.size)
        }
    }

    inner class ViewHolder(private val binding: ItemTramStopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TramDestination) {
            binding.tvTime.text = context.resources.getQuantityString(CR.plurals.minutes, item.minutes, item.minutes)
        }
    }

}