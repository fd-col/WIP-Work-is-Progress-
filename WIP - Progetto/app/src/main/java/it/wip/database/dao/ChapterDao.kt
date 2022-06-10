package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Chapter

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    fun getAll(): Array<Chapter>

    @Insert
    fun insert(vararg user: Chapter)

    @Update
    fun update(user: Chapter)

    @Delete
    fun delete(user: Chapter)

}