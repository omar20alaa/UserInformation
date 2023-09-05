package app.userinformation.database

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "user_db")
            .build()
    }

}