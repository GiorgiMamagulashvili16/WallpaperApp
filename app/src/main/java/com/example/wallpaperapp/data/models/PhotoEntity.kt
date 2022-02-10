package com.example.wallpaperapp.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallpaper")
data class PhotoEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val alt: String,
    @Embedded val src: SrcEntity,
)