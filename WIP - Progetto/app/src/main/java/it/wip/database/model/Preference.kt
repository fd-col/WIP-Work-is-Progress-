package it.wip.database.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "preference",
        primaryKeys = ["user", "setting"],
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["user"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
            ForeignKey(
                entity = Setting::class,
                parentColumns = ["setting_name"],
                childColumns = ["setting"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)
        ])
data class Preference(
    val user: Int,
    val setting: String
)
