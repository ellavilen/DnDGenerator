package com.example.dndgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.view.*

class CharacterAdapter(private val characters: List<Character>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character){
            itemView.tvName.text = character.name
            itemView.tvRace.text = character.race
            itemView.tvClass.text = character.characterClass
            //binds the generated values to the db
        }
    }
    /**
     * Creates and returns a ViewHolder object, inflating the layout called item_reminder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }
    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return characters.size
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}