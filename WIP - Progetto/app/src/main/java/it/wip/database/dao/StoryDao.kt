package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.Story

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getAll(): Array<Story>

    @Insert
    fun insert(vararg user: Story)

    @Update
    fun update(user: Story)

    @Delete
    fun delete(user: Story)

}