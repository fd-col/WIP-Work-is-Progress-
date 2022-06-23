package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Preference

@Dao
interface PreferenceDao {

    @Query("SELECT * FROM preference")
    suspend fun getAll(): Array<Preference>

    @Query("SELECT * FROM preference WHERE user = :userId")
    suspend fun getAllByUser(userId: Int): Array<Preference>

    @Insert
    suspend fun insert(vararg preferences: Preference)

    @Insert
    fun insertWithoutCoroutines(vararg preferences: Preference)

    @Update
    suspend fun update(preference: Preference)

    @Delete
    suspend fun delete(preference: Preference)

    @Query("DELETE FROM preference WHERE(user = :userId AND setting = :setting)")
    suspend fun delete(userId: Int, setting: String)

}