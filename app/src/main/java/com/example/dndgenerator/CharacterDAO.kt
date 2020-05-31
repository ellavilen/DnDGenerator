package com.example.dndgenerator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characterTable")
    fun getAllCharacters(): List<Character>

    @Insert
    suspend fun insertCharacter(character : Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("DELETE FROM characterTable")
    suspend fun deleteAllCharacters()

    @Update
    suspend fun updateCharacter(character: Character)
}