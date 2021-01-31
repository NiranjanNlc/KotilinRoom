package com.example.roomapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class],version = 1)
abstract class MemberDatabase : RoomDatabase() {

    abstract val MemberDao: MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberDatabase::class.java,
                        "Member_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}