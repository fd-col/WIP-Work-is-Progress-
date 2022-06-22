package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Chapter

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    suspend fun getAll(): Array<Chapter>

    @Query("SELECT * FROM chapter WHERE user = :userId")
    fun getAllByUserWithoutCoroutines(userId: Int): Array<Chapter>

    @Insert
    suspend fun insert(vararg chapters: Chapter)

    @Insert
    fun insertWithoutCoroutines(vararg chapters: Chapter)

    @Update
    suspend fun update(chapter: Chapter)

    @Delete
    suspend fun delete(chapter: Chapter)

}