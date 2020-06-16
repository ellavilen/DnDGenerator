package com.example.dndgenerator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.android.synthetic.main.content_saved.*
import kotlinx.android.synthetic.main.item_character.*
import kotlinx.coroutines.*

const val ADD_CHARACTER_REQUEST_CODE = 100
const val TAG = "SavedActivity"


class SavedActivity : AppCompatActivity() {

    private val characters = arrayListOf<Character>()
    private val characterAdapter = CharacterAdapter(characters)
    private lateinit var viewModel: SavedActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Saved characters"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initViewModel()
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
                characters.clear()
                characters.addAll(characters)
                viewModel.deleteAllCharacters()
                Toast.makeText(this,"All saved characters deleted", Toast.LENGTH_SHORT).show()
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
        //getSavedCharacters()
        createItemTouchHelper().attachToRecyclerView(rvSavedCharacters) //swipe to delete

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(SavedActivityViewModel::class.java)

        viewModel.characters.observe(this, Observer { characters->
            this@SavedActivity.characters.clear()
            this@SavedActivity.characters.addAll(characters)
            characterAdapter.notifyDataSetChanged()
        })
    }

    //private fun getSavedCharacters() {}


    private fun createItemTouchHelper(): ItemTouchHelper{
        //swiping left to delete
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            // Enables or Disables the ability to move items up and down.
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder):Boolean{
                return false
            }

            //DELETE ONE CHARACTER
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val characterToDelete = characters[position]
                viewModel.deleteCharacter(characterToDelete)

                characters.removeAt(position)
                characterAdapter.notifyDataSetChanged()

            }
        }
        return ItemTouchHelper(callback)
    }
}
