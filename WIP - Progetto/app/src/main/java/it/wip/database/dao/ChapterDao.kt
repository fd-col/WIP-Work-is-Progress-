package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Chapter

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    suspend fun getAll(): Array<Chapter>

    @Insert
    suspend fun insert(vararg chapters: Chapter)

    @Update
    suspend fun update(user: Chapter)

    @Delete
    suspend fun delete(user: Chapter)

}