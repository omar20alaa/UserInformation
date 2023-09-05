package app.userinformation.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: String
)