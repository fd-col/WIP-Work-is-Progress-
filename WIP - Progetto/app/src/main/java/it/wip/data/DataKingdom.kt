package it.wip.data

data class DataKingdom(val viewType: Int, val title: String, val numChapters: String="Chapters: 1",
                       val date: String ="", val time: String = "") {

    val chapters = numChapters
    val textData = title
    val dateData = date
    val timeData = time
}
