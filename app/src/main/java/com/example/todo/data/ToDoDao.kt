package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.data.models.ToDoData

@Dao //dao felelős azért hogy bizonyos db-beli fgv-ket megtaláljon ezek többnyire sql-lekérdezéseket fognak tartalmazni ez a dao egy interface
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>  //livedata szukseges ehhez igy a ToDoData-t becsomagoljuk egy listaba es azt visszaadjuk egy livedata-ba

    @Insert(onConflict = OnConflictStrategy.IGNORE) //amikor uj adat jon a db-be es ha az adat ugyanaz ami már benne van akkor ignoráljuk azt
    suspend fun insertData(toDoData: ToDoData) //beszuras eseten ezt alkalmazzuk

    //suspend coroutineshez tartozik ami szalkezeles tema
    //it is always a good practice to use coroutines for all your database queryes
}