package com.example.dndgenerator

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dndgenerator.CharacterDAO

class CharacterRepository(context: Context) {
    private var characterDao : CharacterDAO?

    init {
        val characterRoomDatabase = CharacterRoomDatabase.getCharacterRoomDatabase(context)
        characterDao = characterRoomDatabase!!.characterDao()
    }

    fun getAllCharacters(): LiveData<Character>?{
        return characterDao?.getAllCharacters()
    }

    suspend fun insertCharacter(character: Character){
        characterDao?.insertCharacter(character)
    }

    suspend fun deleteCharacter(character: Character){
        characterDao?.deleteCharacter(character)
    }

    suspend fun updateCharacter(character: Character){
        characterDao?.updateCharacter(character)
    }
}