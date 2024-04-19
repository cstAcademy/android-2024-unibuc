package com.cst.cstacademyunibuc

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cst.cstacademyunibuc.data.AppDatabase

class ApplicationController : Application() {

    lateinit var appDatabase: AppDatabase

    companion object {
        var instance: ApplicationController? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        appDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "CST_FMI"
        )
            .fallbackToDestructiveMigration() // DEVELOPMENT ONLY
            .build()
    }

}