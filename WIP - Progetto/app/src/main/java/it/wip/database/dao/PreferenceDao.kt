package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Preference

@Dao
interface PreferenceDao {

    @Query("SELECT * FROM preference")
    suspend fun getAll(): Array<Preference>

    @Insert
    suspend fun insert(vararg user: Preference)

    @Update
    suspend fun update(user: Preference)

    @Delete
    suspend fun delete(user: Preference)

}