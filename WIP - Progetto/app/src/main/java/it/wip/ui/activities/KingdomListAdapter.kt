package it.wip.ui.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom

class KingdomListAdapter(private val context: Context, var list: ArrayList<DataKingdom>,
                         private val itemClickListener: (Int) -> Unit)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val THE_FIRST_VIEW = 1
        const val THE_SECOND_VIEW = 2
        const val THE_THIRD_VIEW = 3
        const val THE_FORTH_VIEW = 4
        const val THE_HORIZONTAL_VIEW = 5
    }
    // viewHolder for the vertical recyclerView into KingdomActivity -cards on the left
    inner class KingdomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var chapters: TextView = itemView.findViewById(R.id.item_chapters)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerViewModel = list[position]
            title.text = recyclerViewModel.textData
            chapters.text = recyclerViewModel.chapters

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }
    // viewHolder for the vertical recyclerView into KingdomActivity -cards on the right
    inner class KingdomViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var chapters: TextView = itemView.findViewById(R.id.item_chapters)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerViewModel2 = list[position]
            title.text = recyclerViewModel2.textData
            chapters.text = recyclerViewModel2.chapters

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }
    // viewHolder for the horizontal recyclerView into KingdomActivity
    inner class KingdomHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.story_header_title)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerHorizontalViewModel = list[position]
            title.text = recyclerHorizontalViewModel.textData

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }
    // viewHolder for the vertical recyclerView into StoryDetailActivity -cards on the left
    inner class KingdomViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var date: TextView = itemView.findViewById(R.id.item_date)
        var time: TextView = itemView.findViewById(R.id.item_time)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerViewModel3 = list[position]
            title.text = recyclerViewModel3.textData
            date.text = recyclerViewModel3.dateData
            time.text = recyclerViewModel3.timeData

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }
    // viewHolder for the vertical recyclerView into StoryDetailActivity -cards on the right
    inner class KingdomViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var date: TextView = itemView.findViewById(R.id.item_date)
        var time: TextView = itemView.findViewById(R.id.item_time)

        fun bind(position: Int, itemClickListener:(Int)->Unit) {
            val recyclerViewModel4 = list[position]
            title.text = recyclerViewModel4.textData
            date.text = recyclerViewModel4.dateData
            time.text = recyclerViewModel4.timeData

            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            THE_FIRST_VIEW -> {
                return KingdomViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.kingdom_story_left_item, parent, false)
                )
            }
            THE_SECOND_VIEW -> return KingdomViewHolder2(
                LayoutInflater.from(context)
                    .inflate(R.layout.kingdom_story_right_item, parent, false)
            )
            THE_THIRD_VIEW -> return KingdomViewHolder3(
                LayoutInflater.from(context)
                    .inflate(R.layout.kingdom_chapter_left_item, parent, false)
            )
            THE_FORTH_VIEW -> return KingdomViewHolder4(
                LayoutInflater.from(context)
                    .inflate(R.layout.kingdom_chapter_right_item, parent, false)
            )
            else -> return KingdomHorizontalViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.kingdom_header_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            list[position].viewType === THE_FIRST_VIEW ->
                (holder as KingdomViewHolder).bind(position, itemClickListener)
            list[position].viewType === THE_SECOND_VIEW ->
                (holder as KingdomViewHolder2).bind(position, itemClickListener)
            list[position].viewType === THE_THIRD_VIEW ->
                (holder as KingdomViewHolder3).bind(position, itemClickListener)
            list[position].viewType === THE_FORTH_VIEW ->
                (holder as KingdomViewHolder4).bind(position, itemClickListener)
            else ->
                (holder as KingdomHorizontalViewHolder).bind(position, itemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}