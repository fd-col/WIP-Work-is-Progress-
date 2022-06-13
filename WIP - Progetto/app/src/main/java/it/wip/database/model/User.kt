package it.wip.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "favourite_avatar")
    val favouriteAvatar: String,
    @ColumnInfo(name = "favourite_background")
    val favouriteBackground: String,
    @ColumnInfo(name = "work_time")
    val workTime: Int,
    val coins: Int
)
