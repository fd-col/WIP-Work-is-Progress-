package it.wip.data

import it.wip.R

data class DataKingdom(val viewType: Int, val title: String,
                       val image: Int = R.drawable.play_button,
                       val date: String ="12/12/2022", val time: String = "2.03.12 min"
                       ) {
    val textData = title
    val dateData = date
    val timeData = time
}
