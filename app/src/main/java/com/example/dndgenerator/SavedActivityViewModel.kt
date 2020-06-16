package com.example.dndgenerator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedActivityViewModel(application: Application) : AndroidViewModel(application){
    private val characterRepo = CharacterRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val characters: LiveData<List<Character>> = characterRepo.getAllCharacters()

    fun insertCharacter(character: Character){
        ioScope.launch {
            characterRepo.insertCharacter(character)
        }
    }

    fun deleteCharacter(character: Character){
        ioScope.launch {
            characterRepo.deleteCharacter(character)
        }
    }

    fun deleteAllCharacters(){
        ioScope.launch {
            characterRepo.deleteAllCharacters()
        }
    }
}