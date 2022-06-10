package it.wip.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "story",
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["user"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
            )
        ]
)
data class Story(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "story_name")
    val storyName: String,
    @ColumnInfo(name = "created_on")
    val createdOn: String,
    val user: Int
)