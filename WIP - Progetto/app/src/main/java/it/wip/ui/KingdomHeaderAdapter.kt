package it.wip.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataHeaderKingdom

class KingdomHeaderAdapter(private val context: Context, var list: ArrayList<DataHeaderKingdom>,
                           private val itemClickListener: (Int) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class KingdomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.story_header_title)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerViewModel = list[position]
            title.text = recyclerViewModel.textData

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return KingdomViewHolder(
            LayoutInflater.from(context).inflate(R.layout.kingdom_header_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as KingdomHeaderAdapter.KingdomViewHolder).bind(position, itemClickListener)
    }

}