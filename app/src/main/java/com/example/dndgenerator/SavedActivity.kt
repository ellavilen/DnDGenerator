package com.example.dndgenerator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.android.synthetic.main.content_saved.*
import kotlinx.android.synthetic.main.item_character.*
import kotlinx.coroutines.*


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

    //go back to main activity
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
        createItemTouchHelper().attachToRecyclerView(rvSavedCharacters) //swipe to delete

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

    private fun createItemTouchHelper(): ItemTouchHelper{
        //swiping left to delete
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            // Enables or Disables the ability to move items up and down.
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder):Boolean{
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val characterToDelete = characters[position]

                //to delete also from db, not only recyclerview
                mainScope.launch {
                    withContext(Dispatchers.IO){
                        characterRepo.deleteCharacter(characterToDelete)
                    }
                }

                characters.removeAt(position)
                characterAdapter.notifyDataSetChanged()

            }
        }
        return ItemTouchHelper(callback)
    }
}
