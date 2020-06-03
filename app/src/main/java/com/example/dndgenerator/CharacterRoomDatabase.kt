package com.example.dndgenerator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

@Database(entities = [Character::class], version = 3, exportSchema = false)

abstract class CharacterRoomDatabase : RoomDatabase(){
        abstract fun characterDao(): CharacterDAO

        companion object {
            private const val DATABASE_NAME = "CHARACTER_DATABASE"

            @Volatile
            private var characterRoomDatabaseInstance: CharacterRoomDatabase? = null

            fun getCharacterRoomDatabase(context: Context): CharacterRoomDatabase? {
                if(characterRoomDatabaseInstance == null){
                    synchronized(CharacterRoomDatabase::class.java){
                        if(characterRoomDatabaseInstance == null){
                            characterRoomDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                CharacterRoomDatabase::class.java, DATABASE_NAME
                            )
                                .fallbackToDestructiveMigration()
                                .build()
                        }
                    }
                }
                return characterRoomDatabaseInstance
            }
        }
}