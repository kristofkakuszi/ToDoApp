package com.example.todo.data.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.ToDoDao
import com.example.todo.data.models.ToDoData

class ToDoRepository(private val toDoDao : ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData() //az osszes adatot eltaroljuk a toDoDao-bol itt

    suspend fun insertData(todoData: ToDoData){
        toDoDao.insertData(todoData)
    }

    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){ //second csing -> ToDoViewModel
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    /**
     * a repository  azt csinalja hogy
     */

}