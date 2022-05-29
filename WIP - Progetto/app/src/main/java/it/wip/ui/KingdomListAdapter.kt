package it.wip.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom

class KingdomListAdapter(private val context: Context, var list: ArrayList<DataKingdom>
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val THE_FIRST_VIEW = 1
        const val THE_SECOND_VIEW = 2
        const val THE_THIRD_VIEW = 3
    }

    inner class KingdomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var image: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            title.text = recyclerViewModel.textData
            image.setImageResource(recyclerViewModel.image)
        }
    }

    inner class KingdomViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var image: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(position: Int) {
            val recyclerViewModel2 = list[position]
            title.text = recyclerViewModel2.textData
            image.setImageResource(recyclerViewModel2.image)
        }
    }
/*
    inner class KingdomViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)

        fun bind(position: Int) {
            val recyclerViewModel3 = list[position]
            title.text = recyclerViewModel3.textData
        }
    }
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == THE_FIRST_VIEW) {
            return KingdomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_view_design, parent, false)
            )
        }
        return KingdomViewHolder2(
                LayoutInflater.from(context)
                    .inflate(R.layout.card_view_design_right, parent, false)
        )
    /*
        return KingdomViewHolder3(
            LayoutInflater.from(context)
                .inflate(R.layout.kingdom_header_item, parent, false)
        )
     */
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === THE_FIRST_VIEW) {
            (holder as KingdomViewHolder).bind(position)
        } else if (list[position].viewType === THE_SECOND_VIEW) {
            (holder as KingdomViewHolder2).bind(position)
        }
        else {
            //(holder as KingdomViewHolder3).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}