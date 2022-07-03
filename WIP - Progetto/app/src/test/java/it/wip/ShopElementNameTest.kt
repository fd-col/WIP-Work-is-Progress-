package it.wip

import it.wip.utils.encouragementQuotes
import it.wip.utils.fromShopElementNameToDescription
import it.wip.utils.fromShopElementNameToLocalizedName
import it.wip.utils.fromShopElementNameToResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ShopElementNameTest { // Testing methods inside "it.wip.utils.ResourceHelper" file
    @Test
    fun findStringResource() {
        val nameResource = fromShopElementNameToLocalizedName("girl_with_pearl_earring")
        assertEquals(R.string.girl_with_a_pearl_earring, nameResource)
    }

    @Test
    fun findDrawableResource() {
        val drawableResource = fromShopElementNameToResource("magritte_apple")
        assertEquals(R.drawable.magritte, drawableResource)
    }

    @Test
    fun findDescriptionResource(){
        val descriptionResource = fromShopElementNameToDescription("the_scream")
        assertEquals(R.string.urlo_di_munch_description, descriptionResource)
    }

}