package com.example.dndgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val characters: List<Character>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(character: Character) {
            itemView.tvSavedName.text = "Name:" + character.name
            itemView.tvSavedRace.text = "Race: " + character.race
            itemView.tvSavedClass.text = "Class: " + character.characterClass
            itemView.tvDescription.text = "Description: " + character.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false) //Inflate item to recycler view
        )
    }
    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}