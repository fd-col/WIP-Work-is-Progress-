package it.wip.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "shopped",
        primaryKeys = ["user", "shop_element"],
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["user"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
            ForeignKey(
                entity = ShopElement::class,
                parentColumns = ["element_name"],
                childColumns = ["shop_element"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
            )
        ])
data class Shopped(
    val user: Int,
    @ColumnInfo(name = "shop_element")
    val shopElement: String,
    @ColumnInfo(name = "bought_on")
    val boughtOn: String
)