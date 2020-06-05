package com.example.dndgenerator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndgenerator.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var characterRepo: CharacterRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val error = MutableLiveData<String?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        characterRepo = CharacterRepository(this)

        btnGenerate.setOnClickListener(){
            setName()
            setRace()
            setClass()
         }

        btnSave.setOnClickListener(){
            saveCharacter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startSavedActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Generate name, race and class
    private fun setName(){
        if (rbFemale.isChecked)  {
            tvName.text = FemaleName.FEMALE_NAMES.random().toString()
        } else {
            tvName.text = (MaleName.MALE_NAMES.random().toString())
        }
    }

    private fun setRace(){
        tvRace.text = Race.RACE.random().toString()
    }

    private fun setClass(){
        tvClass.text = CharacterClass.CLASS.random().toString()
    }

    //save generated character
    private fun saveCharacter(){
        if(tvName.toString().isNotBlank() && tvRace.toString().isNotBlank() && tvClass.toString().isNotBlank()) {
            mainScope.launch {
                val character = Character(
                    name = tvName.toString(),
                    race = tvRace.toString(),
                    characterClass = tvClass.toString(),
                    description = ""
                )
                withContext(Dispatchers.IO) {
                    characterRepo.insertCharacter(character)
                }
                Toast.makeText(this@MainActivity, "Character saved", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Can't save empty character", Toast.LENGTH_SHORT).show()
        }
    }

    /*fun isCharacterValid():Boolean{
        return when {
            tvName.text == null && tvRace.text == null && tvClass.text == null -> {
                error.value = "Can't save empty character!"
                val message = error.value.toString()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                false
            } else -> true
        }
    }*/

    //go to saved characters
    private fun startSavedActivity() {
        val intent = Intent(this, SavedActivity::class.java)
        startActivity(intent)
    }


}
