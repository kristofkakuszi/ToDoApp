package com.example.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.ToDoDatabase
import com.example.todo.data.models.ToDoData
import com.example.todo.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()   //itt megkapjuk a db-t amit atadunk az android viewmodellnek (application) es megkapjuk a referenciajat a toDoDao()-val???????
    private val repository: ToDoRepository

    private val getAllData: LiveData<List<ToDoData>>    //ToDoData dolgait fogja tarolni + itt szeretnenk megkapni az osszes adatot a repositorybol

    init {  //gondolom mivel szalkezeles tema a repositoryhoz kell az init blokk + ToDoViewModell miatt is kellhet ez
        repository = ToDoRepository(toDoDao)    //inicializaljuk a repositoryt es a dao-t atadjuk neki mint parameter
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) { //coroutine hez tartozik gyakorlatilag ezt a background threaden szeretnenk futtatni
            repository.insertData(toDoData)
        //it is always a good practice to run sql querys in a coroutine on a background thread and thats why we use viewmodelscope to launch corouitne to insert, update, delete


        }
    }
}