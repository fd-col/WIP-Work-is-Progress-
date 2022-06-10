package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.ShopElement

@Dao
interface ShopElementDao {

    @Query("SELECT * FROM shop_element")
    fun getAll(): Array<ShopElement>

    @Insert
    fun insert(vararg user: ShopElement)

    @Update
    fun update(user: ShopElement)

    @Delete
    fun delete(user: ShopElement)

}