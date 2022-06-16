package it.wip.database.dao

import androidx.room.*
import it.wip.database.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): Array<User>

    @Query("SELECT * FROM user")
    fun getAllWithoutCoroutines(): Array<User>

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUserById(userId: Int): User

    @Insert
    suspend fun insert(vararg users: User)

    @Insert
    fun insertWithoutCoroutines(vararg users: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

}