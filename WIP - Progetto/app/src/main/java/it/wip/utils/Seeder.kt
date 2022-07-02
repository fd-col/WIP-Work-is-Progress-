package it.wip.utils

import it.wip.database.WIPDatabase
import it.wip.database.model.*
import java.text.SimpleDateFormat
import java.util.*

fun seed(wipDb: WIPDatabase) {

    wipDb.userDao().insertWithoutCoroutines(
        User(1, 30, 120, 500)
    )

    wipDb.shopElementDao().insertWithoutCoroutines(
        ShopElement("magritte_apple", "avatar",0),
        ShopElement("girl_with_pearl_earring", "avatar",0),
        ShopElement("venus", "avatar",0),
        ShopElement("the_scream", "avatar",130),
        ShopElement("self_portrait", "avatar",130),
        ShopElement("david", "avatar",130),

        ShopElement("the_scream_background", "background",0),
        ShopElement("the_persistence_of_memory", "background",0),
        ShopElement("hopper_nighthawks", "background",0),
        ShopElement("creation_of_adam", "background",200),
        ShopElement("lovers", "background",200),
        ShopElement("weathfield_with_crows", "background",200)
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
        Story(1, "Allenamento", "22-06-15 13:02:12", 1),
        Story(2, "Tesi", "22-06-16 19:00:12",1),
        Story(3, "Appunti","22-06-17 10:00:12",1),
        Story(4,"Esame di Analisi 2", "22-06-18 10:00:12", 1),
        Story(5,"Arrampicata", "22-06-23 07:00:12", 1),
        Story(6,"Sistemi Operativi", "22-06-27 16:00:12", 1),
        Story(7,"Programmazione Mobile", "22-06-30 18:00:12", 1),
    )

    wipDb.chapterDao().insertWithoutCoroutines(
        Chapter(1, "Capitolo 1",
            "00:30", "22-06-15 13:02:12", 50,10,1,"venere",1),
        Chapter(2, "Capitolo 1",
            "00:30", "22-06-16 19:00:12", 50,10,2,"venere",2),
        Chapter(3, "Capitolo 2",
            "00:30", "22-06-18 19:00:12",50,10,1,"venere",2),
        Chapter(4, "Capitolo 3",
            "00:30", "22-06-29 19:00:12", 50,10,2,"venere",2),
        Chapter(5, "Capitolo 1",
            "00:30", "22-06-17 10:00:12", 50,10,2,"venere",3),
        Chapter(6,"Capitolo 1",
            "00.30", "22-06-18 10:00:12", 50,10,0,"venere",4),
        Chapter(7, "Capitolo 1",
            "00:30", "22-06-23 07:00:12", 50,10,1,"venere",5),
        Chapter(8,"Capitolo 1",
            "00:30", "22-06-27 16:00:12", 50,10,2,"venere",6),
        Chapter(9, "Capitolo 1",
            "00:30", "22-06-30 18:00:12", 50,10,2,"venere",7)
    )

}