package it.wip.data

data class DataKingdom(val viewType: Int, val title: String,
                       val itemId: Int, val numChapters: String="",
                       val date: String ="12/10/22", val time: String = "1.23.27"
                       ) {
    val textData = title
    val itemID = itemId
    val chapters = numChapters
    val dateData = date
    val timeData = time

}
