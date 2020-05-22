package com.example.dndgenerator

data class Race(
    var race: String
){
    companion object{
        val RACE = listOf(
            "Aarakocra",
            "Aasimar",
            "Bugbear",
            "Centaur",
            "Changeling",
            "Dragonborn",
            "Dwarf",
            "Elf",
            "Feral tiefling",
            "Firbolg",
            "Genasi",
            "Gith",
            "Gnome",
            "Goblin",
            "Goliath" ,
            "Grung",
            "Half-elf",
            "Halfling",
            "Half-orc",
            "Hobgoblin",
            "Human",
            "Kalasthar",
            "Kenku",
            "Kobold",
            "Lizardfolk",
            "Locathah",
            "Loxodon",
            "Minotaur",
            "Orc",
            "Orc of Eberron",
            "Shifter",
            "Simic hybrid",
            "Tabaxi",
            "Tiefling",
            "Tortle",
            "Triton",
            "Vedalken",
            "Verdan",
            "Warforged",
            "Yuan-ti pureblood"
        )
    }
}