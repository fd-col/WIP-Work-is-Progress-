package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Setting

@Dao
interface SettingDao {

    @Query("SELECT * FROM setting")
    suspend fun getAll(): Array<Setting>

    @Insert
    suspend fun insert(vararg user: Setting)

    @Update
    suspend fun update(user: Setting)

    @Delete
    suspend fun delete(user: Setting)

}