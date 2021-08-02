package com.example.todo.fragments

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){ //db-bol jönnek a listák ezért igy kell megoldani
        emptyDatabase.value = toDoData.isEmpty()    //observer, main thread omggg
    }

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(  //ezzel tudjuk majd valtoztani a szinet a priorityknak
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }

    fun verifyDataFromUser(title: String, description: String): Boolean { //mielott hozzaadjuk az adatot a db-hez we check hogy ures e vagy sem. Ha a title vagy a description ures akkor
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> Priority.LOW
        }
    }


    fun parsePriorityToInt(priority: Priority) : Int {
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}