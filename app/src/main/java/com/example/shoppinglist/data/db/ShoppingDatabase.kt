package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(  // required annotation
    entities = [ShoppingItem::class], // could have multiple entities
    version = 1 // must be changed when we change the DB structure
)
abstract class ShoppingDatabase : RoomDatabase() { // must be abstract & inherited from RoomDatabase()
    abstract fun getShoppingDao(): ShoppingDao  // function that refers to our Dao object

    companion object { // equivalent to the static keyword in java
        @Volatile // writes to this instance will be visible to the other threads
        private var instance: ShoppingDatabase? = null // singleton - we make sure only one thread will write
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){ // sync. makes sure no other threads
            instance ?: createDatabase(context).also{ instance = it }
        } // this function is executed whenever we create an instance of this class. f.e. ShoppingDatabase()
        // and we make sure only one thread creates the DB/ set the instance

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }
}