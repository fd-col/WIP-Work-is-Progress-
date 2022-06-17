package it.wip.utils

import it.wip.R

fun fromShopElementNameToLocalizedName(shopElementName: String): Int {

    return when(shopElementName) {
        "venus" -> R.string.venus
        "magritte_apple" -> R.string.the_son_of_man
        "girl_with_pearl_earring" -> R.string.girl_with_a_pearl_earring
        "munch_scream" -> R.string.the_scream
        "self_portrait" -> R.string.van_gogh_self_portrait
        "david" -> R.string.david
        "creation_of_adam" -> R.string.the_creation_of_adam
        "lovers" -> R.string.magritte_kiss
        "weathfield_with_crows" -> R.string.weathfield_with_crows

        "the_scream" -> R.string.the_scream
        "the_persistence_of_memory" -> R.string.the_persistence_of_memory
        "hopper_nighthawks" -> R.string.hopper_nighthawks

        else -> {R.string.app_name}
    }

}

fun fromShopElementNameToResource(shopElementName: String): Int {

    return when(shopElementName) {

        "venus" -> R.drawable.venere
        "magritte_apple" -> R.drawable.magritte
        "girl_with_pearl_earring" -> R.drawable.ragazza_col_turbante
        "munch_scream" -> R.drawable.munch
        "self_portrait" -> R.drawable.van_gogh_self_portrait
        "david" -> R.drawable.david
        "creation_of_adam" -> R.drawable.adam_off_stand
        "lovers" -> R.drawable.magritte_kiss_off_stand
        "weathfield_with_crows" -> R.drawable.field_with_crows_off_stand
        "the_scream" -> R.drawable.munch_off_stand
        "the_persistence_of_memory" -> R.drawable.dali_off_stand
        "hopper_nighthawks" -> R.drawable.hopper_off_stand

        else -> { R.drawable.venere }
    }

}

fun fromShopElementNameToDescription(shopElementName: String): Int {

    return when(shopElementName) {
        "venus" -> R.string.venere_description
        "magritte_apple" -> R.string.magritte_kiss_description
        "girl_with_pearl_earring" -> R.string.girl_with_a_pearl_earring //Da modificare
        "munch_scream" -> R.string.urlo_di_munch_description
        "self_portrait" -> R.string.van_gogh_autoritratto_description
        "david" -> R.string.david_description
        "creation_of_adam" -> R.string.creazione_di_adamo_description
        "lovers" -> R.string.magritte_kiss_description
        "weathfield_with_crows" -> R.string.van_gogh_field_with_crows_description

        else -> { R.string.app_name }
    }

}