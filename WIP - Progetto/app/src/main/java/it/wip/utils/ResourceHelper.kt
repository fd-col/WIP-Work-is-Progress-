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

fun fromShopElementNameToDescription(shopElementName: String): Int {
    return when(shopElementName) {
        "Venere" -> R.string.venere_description
        "Magritte" -> R.string.magritte_kiss_description
        "Ragazza col turbante" -> R.string.ragazza_col_turbante //Da modificare
        "L'urlo" -> R.string.urlo_di_munch_description
        "Creazione di Adamo" -> R.string.creazione_di_adamo_description

        else -> {R.string.app_name}
    }

}