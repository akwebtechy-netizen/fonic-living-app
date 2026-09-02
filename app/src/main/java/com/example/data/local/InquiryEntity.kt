package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inquiries")
data class InquiryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: String,
    val productName: String,
    val category: String,
    val selectedWood: String,
    val dimensions: String,
    val selectedFinish: String,
    val clientName: String,
    val clientCity: String,
    val clientCountry: String,
    val specialNotes: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val productId: String,
    val addedTimestamp: Long = System.currentTimeMillis()
)
