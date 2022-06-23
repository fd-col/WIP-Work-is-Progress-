package it.wip.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "favourite_avatar")
    var favouriteAvatar: String,
    @ColumnInfo(name = "work_time")
    var studyTime: Int,
    @ColumnInfo(name = "max_work_time")
    var maxStudyTime: Int,
    var coins: Int
)
