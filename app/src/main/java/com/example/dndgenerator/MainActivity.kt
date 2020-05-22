package com.example.dndgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnGenerate.setOnClickListener(){
            setName()
            setRace()
            setClass()
         }
    }
    private fun setName(){
        if(rbFemale.isChecked) {
            tvName.setText(FemaleName.FEMALE_NAMES.random())
        } else tvName.setText(MaleName.MALE_NAMES.random())
    }

    private fun setRace(){
        tvRace.setText(Race.RACE.random())
    }

    private fun setClass(){
        tvClass.setText(CharacterClass.CLASS.random())
    }
}
