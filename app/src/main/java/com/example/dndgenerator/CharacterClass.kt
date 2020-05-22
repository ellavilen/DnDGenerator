package com.example.dndgenerator

data class CharacterClass(
    var characterClass: String
){
    companion object{
        val CLASS = listOf(
            "Artificer",
            "Barbarian",
            "Bard",
            "Blood hunter",
            "Cleric",
            "Druid",
            "Fighter",
            "Monk",
            "Paladin",
            "Ranger",
            "Rogue",
            "Sorcerer",
            "Warlock",
            "Wizard"
        )
    }
}