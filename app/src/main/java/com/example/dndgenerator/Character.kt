package com.example.dndgenerator

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "characterTable")
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "race")
    var race: String,

    @ColumnInfo(name = "class")
    var characterClass: String,

    @ColumnInfo(name = "description")
    var description: String

) : Parcelable

