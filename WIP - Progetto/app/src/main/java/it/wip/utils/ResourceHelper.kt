package it.wip.utils

import it.wip.R

fun fromAvatarNameToResource(avatarName: String): Int {

    return when(avatarName) {

        "Venere" -> R.drawable.venere
        "Magritte" -> R.drawable.magritte
        "Ragazza col turbante" -> R.drawable.ragazza_col_turbante

        else -> {
            R.drawable.venere
        }
    }

}