package com.example.dndgenerator

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "characterTable")
data class Character(
    @ColumnInfo(name = "character")
    var name: String,
    var race: String,
    var characterClass: String,
    var description: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable
