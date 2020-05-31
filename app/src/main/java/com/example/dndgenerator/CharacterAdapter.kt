package com.example.dndgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.item_character.view.*
import java.text.FieldPosition

class CharacterAdapter(private val characters: List<Character>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false) //Inflate item to recycler view
        )
    }
    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(characters[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(character: Character){
            itemView.tvName.text = character.name
            itemView.tvRace.text = character.race
            itemView.tvClass.text = character.characterClass
        }
    }
}