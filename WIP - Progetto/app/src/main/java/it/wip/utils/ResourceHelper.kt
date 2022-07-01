package it.wip.utils

import it.wip.R

fun fromShopElementNameToLocalizedName(shopElementName: String): Int {

    return when(shopElementName) {
        "venus" -> R.string.venus
        "magritte_apple" -> R.string.the_son_of_man
        "girl_with_pearl_earring" -> R.string.girl_with_a_pearl_earring
        "the_scream" -> R.string.the_scream
        "self_portrait" -> R.string.van_gogh_self_portrait
        "david" -> R.string.david
        "creation_of_adam" -> R.string.the_creation_of_adam
        "lovers" -> R.string.magritte_kiss
        "weathfield_with_crows" -> R.string.weathfield_with_crows
        "the_scream_background" -> R.string.the_scream
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
        "the_scream" -> R.drawable.munch
        "self_portrait" -> R.drawable.van_gogh_self_portrait
        "david" -> R.drawable.david
        "creation_of_adam" -> R.drawable.adam_off_stand
        "lovers" -> R.drawable.magritte_kiss_off_stand
        "weathfield_with_crows" -> R.drawable.field_with_crows_off_stand
        "the_scream_background" -> R.drawable.munch_off_stand
        "the_persistence_of_memory" -> R.drawable.dali_off_stand
        "hopper_nighthawks" -> R.drawable.hopper_off_stand

        else -> { R.drawable.venere }
    }

}

fun fromShopElementNameToDescription(shopElementName: String): Int {

    return when(shopElementName) {
        "venus" -> R.string.venere_description
        "magritte_apple" -> R.string.magritte_apple_description
        "girl_with_pearl_earring" -> R.string.girl_with_a_pearl_earring_description //Da modificare
        "the_scream" -> R.string.urlo_di_munch_description
        "self_portrait" -> R.string.van_gogh_autoritratto_description
        "david" -> R.string.david_description
        "creation_of_adam" -> R.string.creazione_di_adamo_description
        "lovers" -> R.string.magritte_kiss_description
        "weathfield_with_crows" -> R.string.van_gogh_field_with_crows_description
        "the_scream_background" -> R.string.urlo_di_munch_description
        "the_persistence_of_memory" -> R.string.the_persistence_of_memory_desciption

        else -> { R.string.app_name }
    }

}


//          FUNZIONE PER LA RACCOLTA DELLE FRASI DI INCORAGGIAMENTO
fun encouragementQuotes():MutableList<Int>{
    val allQuotes = mutableListOf<Int>()
    val quote1: Int = R.string.incoraggiamento_1
    val quote2: Int = R.string.incoraggiamento_2
    val quote3: Int = R.string.incoraggiamento_3
    val quote4: Int = R.string.incoraggiamento_4
    val quote5: Int = R.string.incoraggiamento_5
    val quote6: Int = R.string.incoraggiamento_6

    allQuotes.add(quote1)
    allQuotes.add(quote2)
    allQuotes.add(quote3)
    allQuotes.add(quote4)
    allQuotes.add(quote5)
    allQuotes.add(quote6)

    return allQuotes
}


//          FUNZIONE PER LA RACCOLTA DELLE FRASI DI PAUSA
fun pauseQuotes():MutableList<Int>{
    val allQuotes = mutableListOf<Int>()
    val quote1: Int = R.string.pausa_1
    val quote2: Int = R.string.pausa_2
    val quote3: Int = R.string.pausa_3
    val quote4: Int = R.string.pausa_4

    allQuotes.add(quote1)
    allQuotes.add(quote2)
    allQuotes.add(quote3)
    allQuotes.add(quote4)

    return allQuotes
}