package com.example.shoppinglist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem (
    @ColumnInfo(name="item_name") // optional for naming purposes
    var name: String,
    @ColumnInfo(name="item_amount") // optional. w/o this column name will be amount
    var amount: Int
) {
    @PrimaryKey(autoGenerate = true) // required by sql, room will autogenerate the id
    var id: Int? = null // nullable integer
}