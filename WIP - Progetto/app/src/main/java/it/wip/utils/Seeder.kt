package it.wip.utils

import it.wip.R
import it.wip.database.WIPDatabase
import it.wip.database.model.*
import java.text.SimpleDateFormat
import java.util.*

fun seed(wipDb: WIPDatabase) {

    wipDb.userDao().insertWithoutCoroutines(
        User(1, "Venere", 30, 120, 150)
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
        Story(1, "Allenamento", dateFormat.format(Date()).toString(), 1),
        Story(2, "Cazzeggio", "16-06-22 19:00:12",1),
        Story(3, "Studio","17-06-22 10:00:12",1),
        Story(4,"Esame di Analisi 2", dateFormat.format(Date()).toString(), 1),
        Story(5,"Testo Lunghissimoooooooooooo", dateFormat.format(Date()).toString(), 1),
        Story(6,"Sistemi Operativi", dateFormat.format(Date()).toString(), 1),
        Story(7,"Progr@mmazin& M0bil&", "01-06-22 11:00:12", 1),
    )

    wipDb.chapterDao().insertWithoutCoroutines(
        Chapter(1, "Capitol1",
            "00:30", "21/06/22 10.20.20", 50,10,1,"venere",1),
        Chapter(2, "Capitol1",
            "00:30", "20/06/22 15.20.20", 50,10,0,"venere",2),
        Chapter(3, "Capitol2",
            "00:30", "19/06/22 16.20.20",50,10,1,"venere",2),
        Chapter(4, "Capitol3",
            "00:30", "18/06/22 16.20.20", 50,10,0,"venere",2),
        Chapter(5, "Capitol1",
            "00:30", "17/06/22 17.20.20", 50,10,1,"venere",5),
        Chapter(6, "Capitol1",
            "00:30", "16/06/22 18.20.20", 50,10,0,"venere",3),
        Chapter(7, "Capitol1",
            "00:30", "15/06/22 19.20.20", 50,10,1,"venere",7),
        Chapter(8,"Capitol1",
            "00.30", "21/06/22 22.20.20", 50,10,0,"venere",4),
        Chapter(9,"Capitol1",
            "00:30", "21/06/22 12.20.20", 50,10,1,"venere",6)
    )

    wipDb.settingDao().insertWithoutCoroutines(
        Setting("lefthand_mode"),
        Setting("silent_mode"),
        Setting("hardcore_mode")
    )

    wipDb.preferenceDao().insertWithoutCoroutines(
        Preference(1, "lefthand_mode")
    )

}