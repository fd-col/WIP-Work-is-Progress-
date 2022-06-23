package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Setting

@Dao
interface SettingDao {

    @Query("SELECT * FROM setting")
    suspend fun getAll(): Array<Setting>

    @Insert
    suspend fun insert(vararg settings: Setting)

    @Insert
    fun insertWithoutCoroutines(vararg setting: Setting)

    @Update
    suspend fun update(setting: Setting)

    @Delete
    suspend fun delete(setting: Setting)

}