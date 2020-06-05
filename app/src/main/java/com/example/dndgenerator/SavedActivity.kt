package com.example.dndgenerator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.android.synthetic.main.content_saved.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedActivity : AppCompatActivity() {


    private val characters = arrayListOf<Character>()
    private val characterAdapter = CharacterAdapter(characters)

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var characterRepo: CharacterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Saved characters"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        characterRepo = CharacterRepository(this)

        initView()
    }

    //inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_saved, menu)
        return true
    }

    //menu action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                clearCharacterHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        rvSavedCharacters.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvSavedCharacters.adapter = characterAdapter
        rvSavedCharacters.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        getSavedCharacters()
    }

    private fun getSavedCharacters() {
        mainScope.launch {
            val charList = withContext(Dispatchers.IO) {
                characterRepo.getAllCharacters()
            }
            this@SavedActivity.characters.clear()
            this@SavedActivity.characters.addAll(charList)
            this@SavedActivity.characters.reverse()
            this@SavedActivity.characterAdapter.notifyDataSetChanged()
        }
    }

    private fun clearCharacterHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO){
                characterRepo.deleteAllCharacters()
            }
            getSavedCharacters()
            Toast.makeText(this@SavedActivity, "All saved characters deleted.", Toast.LENGTH_LONG).show()
        }
    }
}
