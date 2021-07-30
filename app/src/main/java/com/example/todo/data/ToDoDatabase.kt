package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.data.models.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false) //ezzel mondjuk meg a roomnak hogy ez egy db lesz
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() { //kell az abstract és az örököltetés a roomdb-bol

    abstract fun toDoDao(): ToDoDao //visszaadja a toDoDao-t

    companion object{ //19vidi

        @Volatile
        private var INSTANCE : ToDoDatabase? = null

        /**
         * a kovi metodus: csak egy instance kell hogy legyen a db-ről. eloszor is megnezzuk hogy nem nulla e a db. ha az instance mar letezik akkor ugyanazt visszaadja
         * ha nincs instance vagy ha az instance null akkor synchronized-el megvalositjuk a db-t
         * azert hasznalunk instancet mert nem szeretnenk hogy tobb szal tobb instancet hozzon letre es a synchronized blokkal akarjuk azt elerni hogy csak 1 szal tudja ezt elerni,
         * es csak 1 szal tudja ezt letrehozni
         *
         */

        fun getDatabase(context: Context):ToDoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){    //ezzel nézzük meg tutira hogy nem null e a db
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}