package it.wip.ui

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import it.wip.R

class KingdomListAdapter(
    private val context: Context,
    private val images: Array<Int>
    ): RecyclerView.Adapter<KingdomListAdapter.KingdomViewHolder>(){

    class KingdomViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = itemView.findViewById<ImageView>(R.id.item_image)

        fun bindView(image: Image) {
            // img.setImageResource()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KingdomViewHolder = KingdomViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_design, parent, false))


    override fun onBindViewHolder(holder: KingdomViewHolder, position: Int) {
        //holder.bindView(images[position])
    }

    override fun getItemCount(): Int = images.size

}