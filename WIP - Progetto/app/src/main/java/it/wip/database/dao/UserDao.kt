package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): Array<User>

    @Insert
    suspend fun insert(vararg user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

}