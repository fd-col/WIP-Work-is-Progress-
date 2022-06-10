package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Array<User>

    @Insert
    fun insert(vararg user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

}