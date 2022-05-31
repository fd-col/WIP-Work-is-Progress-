package it.wip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R

class AdapterStoryStarted(storyPieces: MutableList<String>): RecyclerView.Adapter<AdapterStoryStarted.SSViewHolder>() {

    private val description = storyPieces
    /*
    private val description = arrayOf(a, R.string.venere_dali_step_2,
        R.string.venere_dali_break_1, R.string.venere_dali_step_3, R.string.venere_dali_step_4,
        R.string.venere_dali_ending)
    * */

    private val images = intArrayOf(R.drawable.venere, R.drawable.venere, R.drawable.bonfire,
        R.drawable.venere, R.drawable.venere, R.drawable.bonfire)

    inner class SSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImage: ImageView
        var pieceDescription: TextView

        init {
            avatarImage = itemView.findViewById(R.id.avatar_image)
            pieceDescription = itemView.findViewById(R.id.piece_description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SSViewHolder {
        val layout = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_story_started_pieces, viewGroup, false)
        return SSViewHolder(layout)
    }

    override fun onBindViewHolder(viewHolder: SSViewHolder, i: Int) {
        viewHolder.pieceDescription.text = description[i]
        viewHolder.avatarImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return description.size
    }
}