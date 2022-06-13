package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.ShopElement

@Dao
interface ShopElementDao {

    @Query("SELECT * FROM shop_element")
    suspend fun getAll(): Array<ShopElement>

    @Insert
    suspend fun insert(vararg user: ShopElement)

    @Update
    suspend fun update(user: ShopElement)

    @Delete
    suspend fun delete(user: ShopElement)

}