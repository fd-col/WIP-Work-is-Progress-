package it.wip.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_element")
data class ShopElement(
    @PrimaryKey
    @ColumnInfo(name = "element_name")
    val elementName: String,
    val type: String,
    val description: String,
    val price: Int
)