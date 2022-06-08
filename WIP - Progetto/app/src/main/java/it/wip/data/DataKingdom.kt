package it.wip.data

import it.wip.R

data class DataKingdom(val viewType: Int, val title: String,
                       val image: Int = R.drawable.play_button,
                       val date: String ="", val time: String = ""
                       ) {
    val textData = title
    val dateData = date
    val timeData = time
}
