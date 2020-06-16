package com.example.dndgenerator

import android.content.Context
import androidx.lifecycle.LiveData

class CharacterRepository(context: Context) {
    private var characterDao : CharacterDAO

    init {
        val database = CharacterRoomDatabase.getCharacterRoomDatabase(context)
        characterDao = database!!.characterDao()
    }

    fun getAllCharacters(): LiveData<List<Character>>{
        return characterDao.getAllCharacters()
    }

    suspend fun insertCharacter(character: Character){
        characterDao.insertCharacter(character)
    }

    suspend fun deleteCharacter(character: Character){
        characterDao.deleteCharacter(character)
    }

    suspend fun deleteAllCharacters(){
        characterDao.deleteAllCharacters()
    }
}