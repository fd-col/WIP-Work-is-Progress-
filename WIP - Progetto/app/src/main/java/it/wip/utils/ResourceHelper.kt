package it.wip.utils

import it.wip.R

fun fromShopElementNameToResource(avatarName: String): Int {

    return when(avatarName) {

        "Venere" -> R.drawable.venere
        "Il figlio dell'uomo" -> R.drawable.magritte
        "Ragazza col turbante" -> R.drawable.ragazza_col_turbante
        "L'urlo" -> R.drawable.munch
        "Autoritratto" -> R.drawable.van_gogh_self_portrait
        "David" -> R.drawable.david
        "Creazione di Adamo" -> R.drawable.adam_off_stand
        "Gli amanti" -> R.drawable.magritte_kiss_off_stand
        "Campo di grano con volo di corvi" -> R.drawable.field_with_crows_off_stand

        else -> {
            R.drawable.venere
        }
    }

}

fun fromShopElementNameToDescription(shopElementName: String): Int {
    return when(shopElementName) {
        "Venere" -> R.string.venere_description
        "Il figlio dell'uomo" -> R.string.magritte_kiss_description
        "Ragazza col turbante" -> R.string.ragazza_col_turbante //Da modificare
        "L'urlo" -> R.string.urlo_di_munch_description
        "Autoritratto" -> R.string.van_gogh_autoritratto_description
        "David" -> R.string.david_description
        "Creazione di Adamo" -> R.string.creazione_di_adamo_description
        "Gli amanti" -> R.string.magritte_kiss_description
        "Campo di grano con volo di corvi" -> R.string.van_gogh_field_with_crows_description

        else -> {R.string.app_name}
    }

}