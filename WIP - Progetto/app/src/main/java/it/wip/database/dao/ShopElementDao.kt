package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.ShopElement

@Dao
interface ShopElementDao {

    @Query("SELECT * FROM shop_element")
    suspend fun getAll(): Array<ShopElement>

    @Insert
    suspend fun insert(vararg shopElements: ShopElement)

    @Insert
    fun insertWithoutCoroutines(vararg shopElements: ShopElement)

    @Update
    suspend fun update(shopElement: ShopElement)

    @Delete
    suspend fun delete(shopElement: ShopElement)

}