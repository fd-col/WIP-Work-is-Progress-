package it.wip.utils

import it.wip.database.WIPDatabase
import it.wip.database.model.ShopElement
import it.wip.database.model.Shopped
import it.wip.database.model.Story
import it.wip.database.model.User
import java.text.SimpleDateFormat
import java.util.*

fun seed(wipDb: WIPDatabase) {

    wipDb.userDao().insertWithoutCoroutines(
        User(1, "Venere", 30F, 120F, 30)
    )

    wipDb.shopElementDao().insertWithoutCoroutines(
        ShopElement("Venere", "avatar", "", 0),
        ShopElement("Magritte", "avatar", "", 0),
        ShopElement("Ragazza col turbante", "avatar", "", 0),
        ShopElement("L'urlo", "avatar", "", 100),
        ShopElement("Creazione di Adamo", "background", "", 250),
    )

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ITALY)

    wipDb.shoppedDao().insertWithoutCoroutines(
        Shopped(1, "Venere", dateFormat.format(Date())),
        Shopped(1, "Magritte", dateFormat.format(Date())),
        Shopped(1, "Ragazza col turbante", dateFormat.format(Date()))
    )

    wipDb.storyDao().insertWithoutCoroutines(
        Story(1, "Prova", "15/06/2022", 1)
    )
}