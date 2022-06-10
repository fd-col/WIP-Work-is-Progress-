package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Preference

@Dao
interface PreferenceDao {

    @Query("SELECT * FROM preference")
    fun getAll(): Array<Preference>

    @Insert
    fun insert(vararg user: Preference)

    @Update
    fun update(user: Preference)

    @Delete
    fun delete(user: Preference)

}