package com.example.todo.data.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.ToDoDao
import com.example.todo.data.models.ToDoData

class ToDoRepository(private val toDoDao : ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData() //az osszes adatot eltaroljuk a toDoDao-bol itt

    suspend fun insertData(todoData: ToDoData){
        toDoDao.insertData(todoData)

    }

    /**
     * a repository  azt csinalja hogy
     */

}