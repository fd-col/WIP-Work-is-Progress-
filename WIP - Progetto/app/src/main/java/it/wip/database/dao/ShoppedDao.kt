package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Shopped

@Dao
interface ShoppedDao {

    @Query("SELECT * FROM shopped")
    fun getAll(): Array<Shopped>

    @Insert
    fun insert(vararg user: Shopped)

    @Update
    fun update(user: Shopped)

    @Delete
    fun delete(user: Shopped)

}