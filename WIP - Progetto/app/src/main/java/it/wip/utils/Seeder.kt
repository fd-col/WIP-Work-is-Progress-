package it.wip.utils

import it.wip.database.WIPDatabase
import it.wip.database.model.*
import java.text.SimpleDateFormat
import java.util.*

fun seed(wipDb: WIPDatabase) {

    wipDb.userDao().insertWithoutCoroutines(
        User(1, "Venere", 30F, 120F, 150)
    )

    wipDb.shopElementDao().insertWithoutCoroutines(
        ShopElement("magritte_apple", "avatar", "", 0),
        ShopElement("girl_with_pearl_earring", "avatar", "", 0),
        ShopElement("venus", "avatar", "", 0),
        ShopElement("the_scream", "avatar", "", 100),
        ShopElement("self_portrait", "avatar", "", 130),
        ShopElement("david", "avatar", "", 200),

        ShopElement("the_scream_background", "background", "", 0),
        ShopElement("the_persistence_of_memory", "background", "", 0),
        ShopElement("hopper_nighthawks", "background", "", 0),
        ShopElement("creation_of_adam", "background", "", 250),
        ShopElement("lovers", "background", "", 200),
        ShopElement("weathfield_with_crows", "background", "", 300)
    )

    val dateFormat = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ITALY)

    wipDb.shoppedDao().insertWithoutCoroutines(
        Shopped(1, "girl_with_pearl_earring", dateFormat.format(Date())),
        Shopped(1, "magritte_apple", dateFormat.format(Date())),
        Shopped(1, "venus", dateFormat.format(Date())),

        Shopped(1, "the_scream_background", dateFormat.format(Date())),
        Shopped(1, "the_persistence_of_memory", dateFormat.format(Date())),
        Shopped(1, "hopper_nighthawks", dateFormat.format(Date()))
    )

    wipDb.storyDao().insertWithoutCoroutines(
        Story(1, "Allenamento", "15-06-22 11:00:12", 1),
        Story(2, "Cazzeggio", "16-06-22 19:00:12",1),
        Story(3, "Studio","17-06-22 10:00:12",1),
        Story(4,"Esame di Analisi 2", dateFormat.format(Date()).toString(), 1),
        Story(5,"Testo Lunghissimoooooooooooo", dateFormat.format(Date()).toString(), 1),
        Story(6,"Sistemi Operativi", dateFormat.format(Date()).toString(), 1),
        Story(7,"Progr@mmazin& M0bil&", dateFormat.format(Date()).toString(), 1),
    )

    wipDb.chapterDao().insertWithoutCoroutines(
        Chapter(1, "Capitol1",
            "00.05.01", "11/06/22 10.20.20", 1, 1),
        Chapter(2, "Capitol2",
        "1.07.12", "12/06/22 15.20.20", 2, 1),
        Chapter(3, "Capitol3",
        "2.07.56", "13/06/22 16.20.20", 2, 1),
        Chapter(4, "Capitol4",
        "4.01.26", "14/06/22 16.20.20", 2, 1),
        Chapter(5, "Capitol5",
            "01.23.45", "15/06/22 17.20.20", 5, 1),
        Chapter(6, "Capitol6",
            "00.07.02", "16/06/22 18.20.20", 3, 1),
        Chapter(7, "Capitol7",
            "03.21.32", "17/06/22 19.20.20", 7, 1)
    )

}