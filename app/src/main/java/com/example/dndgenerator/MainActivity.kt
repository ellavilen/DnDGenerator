package com.example.dndgenerator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var characterRepo: CharacterRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        characterRepo = CharacterRepository(this)

        initView()
    }

    private fun initView() {
        btnGenerate.setOnClickListener() {
            setName()
            setRace()
            setClass()
        }

        btnSave.setOnClickListener() {
            saveCharacter()
        }
    }


    //inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //function for menu item
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
    private fun setName() {
        if (rbFemale.isChecked) {
            tvName.text = FemaleName.FEMALE_NAMES.random().toString()
        } else {
            tvName.text = (MaleName.MALE_NAMES.random().toString())
        }
    }

    private fun setRace() {
        tvRace.text = Race.RACE.random().toString()
    }

    private fun setClass() {
        tvClass.text = CharacterClass.CLASS.random().toString()
    }

    //save generated character
    private fun saveCharacter() {
        if (tvName.text.toString().isNotBlank() && tvRace.text.toString().isNotBlank() && tvClass.text.toString().isNotBlank()) {
            mainScope.launch {
                val character = Character(
                    name = tvName.text.toString(),
                    race = tvRace.text.toString(),
                    characterClass = tvClass.text.toString(),
                    description = etDescription.text.toString()
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

    //go to saved characters
    private fun startSavedActivity() {
        val intent = Intent(this, SavedActivity::class.java)
        startActivity(intent)
    }
}
