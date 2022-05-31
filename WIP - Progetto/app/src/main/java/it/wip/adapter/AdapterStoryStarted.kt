package it.wip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R

class AdapterStoryStarted(storyPieces: MutableList<String>): RecyclerView.Adapter<AdapterStoryStarted.SSViewHolder>() {

    //NOTA: tra i parametri formali del costruttore passare anche il cronometro, così le stringhe
    //e le immagini vengono caricate in base al tempo che passa
    private val description = storyPieces

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