package it.wip.utils

import it.wip.database.WIPDatabase
import it.wip.database.model.*
import java.text.SimpleDateFormat
import java.util.*

fun seed(wipDb: WIPDatabase) {

    wipDb.userDao().insertWithoutCoroutines(
        User(1, "Venere", 30F, 120F, 30)
    )

    wipDb.shopElementDao().insertWithoutCoroutines(
        ShopElement("magritte_apple", "avatar", "", 0),
        ShopElement("girl_with_pearl_earring", "avatar", "", 0),
        ShopElement("venus", "avatar", "", 0),
        ShopElement("munch_scream", "avatar", "", 100),
        ShopElement("self_portrait", "avatar", "", 130),
        ShopElement("david", "avatar", "", 200),
        ShopElement("creation_of_adam", "background", "", 250),
        ShopElement("lovers", "background", "", 200),
        ShopElement("weathfield_with_crows", "background", "", 300),

        ShopElement("the_scream", "background", "", 0),
        ShopElement("the_persistence_of_memory", "background", "", 0),
        ShopElement("hopper_nighthawks", "background", "", 0)
    )

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY)

    wipDb.shoppedDao().insertWithoutCoroutines(
        Shopped(1, "girl_with_pearl_earring", dateFormat.format(Date())),
        Shopped(1, "magritte_apple", dateFormat.format(Date())),
        Shopped(1, "venus", dateFormat.format(Date())),

        Shopped(1, "the_scream", dateFormat.format(Date())),
        Shopped(1, "the_persistence_of_memory", dateFormat.format(Date())),
        Shopped(1, "hopper_nighthawks", dateFormat.format(Date()))
    )

    wipDb.storyDao().insertWithoutCoroutines(
        Story(1, "Prova1", "15-06-2022 11:00:12", 1),
        Story(2, "Prova2", "16-06-2022 19:00:12",1),
        Story(3, "Prova3","17-06-2022 10:00:12",1),
        Story(4,"Prova4", dateFormat.format(Date()).toString(), 1),
        Story(5,"Prova5", dateFormat.format(Date()).toString(), 1),
        Story(6,"Prova6", dateFormat.format(Date()).toString(), 1),
        Story(7,"Prova7", dateFormat.format(Date()).toString(), 1),
    )

    wipDb.chapterDao().insertWithoutCoroutines(
        Chapter(1, "Capitolo1",
            "1.07.56", "17/06/22 10.20.20", 1, 1),
        Chapter(2, "Capitolo2",
        "1.07.56", "17/06/22 15.20.20", 3, 1),
        Chapter(3, "Capitolo3",
        "1.07.56", "17/06/22 16.20.20", 3, 1),
        Chapter(4, "Capitolo4",
        "1.07.56", "17/06/2022 16.20.20", 2, 1),
        Chapter(5, "Capitolo5",
            "1.07.56", "17/06/22 17.20.20", 2, 1),
        Chapter(6, "Capitolo6",
            "1.07.56", "17/06/22 18.20.20", 1, 1),
        Chapter(7, "Capitolo7",
            "1.07.56", "17/06/22 19.20.20", 1, 1)
    )

}