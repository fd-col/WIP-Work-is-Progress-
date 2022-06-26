package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Chapter

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    suspend fun getAll(): Array<Chapter>

    @Query("SELECT * FROM chapter WHERE story = :chapterId")
    fun getAllById(chapterId: Int): Array<Chapter>

    @Query("SELECT * FROM chapter WHERE story = :storyId")
    fun getAllByStory(storyId: Int): Array<Chapter>

    @Insert
    suspend fun insert(vararg chapters: Chapter)

    @Insert
    fun insertWithoutCoroutines(vararg chapters: Chapter)

    @Update
    suspend fun update(chapter: Chapter)

    @Delete
    suspend fun delete(chapter: Chapter)

}