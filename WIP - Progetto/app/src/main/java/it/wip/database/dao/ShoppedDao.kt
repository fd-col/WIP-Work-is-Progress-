package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Shopped

@Dao
interface ShoppedDao {

    @Query("SELECT * FROM shopped")
    suspend fun getAll(): Array<Shopped>

    @Insert
    suspend fun insert(vararg user: Shopped)

    @Update
    suspend fun update(user: Shopped)

    @Delete
    suspend fun delete(user: Shopped)

}