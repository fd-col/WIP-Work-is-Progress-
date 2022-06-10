package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Setting

@Dao
interface SettingDao {

    @Query("SELECT * FROM setting")
    fun getAll(): Array<Setting>

    @Insert
    fun insert(vararg user: Setting)

    @Update
    fun update(user: Setting)

    @Delete
    fun delete(user: Setting)

}