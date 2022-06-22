package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Shopped

@Dao
interface ShoppedDao {

    @Query("SELECT * FROM shopped")
    suspend fun getAll(): Array<Shopped>

    @Query("SELECT * FROM shopped WHERE user = :userId ORDER BY bought_on ASC")
    suspend fun getAllByUser(userId: Int): Array<Shopped>

    @Insert
    suspend fun insert(vararg shopped: Shopped)

    @Insert
    fun insertWithoutCoroutines(vararg shopped: Shopped)

    @Update
    suspend fun update(shopped: Shopped)

    @Delete
    suspend fun delete(shopped: Shopped)

}