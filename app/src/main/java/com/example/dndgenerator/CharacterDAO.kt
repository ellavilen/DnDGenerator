package com.example.dndgenerator

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characterTable")
    fun getAllCharacters(): LiveData<Character>

    @Insert
    suspend fun insertCharacter(character : Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Update
    suspend fun updateCharacter(character: Character)
}