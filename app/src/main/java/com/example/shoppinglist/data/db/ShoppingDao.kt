package com.example.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.data.db.entities.ShoppingItem

@Dao    // required annotation so that the room will understand this is the Dao interface
interface ShoppingDao {
    // SQL does not let us call these in the same thread - meaning we need to call them asynchronously
    // Thread or coroutines - so we add suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE) // this makes the SQL behave as we want
    suspend fun upsert(item: ShoppingItem) // the purpose will be basically a mix of insert & update

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("Select * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>> // normally the function would return just the list of ShoppingItems
    // the LiveData will make it efficient to update the Recycler View
}