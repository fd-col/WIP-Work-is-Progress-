package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Story
import it.wip.database.model.User

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    suspend fun getAll(): Array<Story>

    @Insert
    suspend fun insert(vararg stories: Story)

    @Insert
    fun insertWithoutCoroutines(vararg stories: Story)

    @Update
    suspend fun update(user: Story)

    @Delete
    suspend fun delete(user: Story)

}