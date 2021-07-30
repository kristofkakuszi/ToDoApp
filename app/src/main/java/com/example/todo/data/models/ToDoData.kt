package com.example.todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.data.models.Priority

@Entity(tableName = "todo_table")
data class ToDoData ( //entity egy táblát reprezentál az db-ben

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)


// ha jól értem annyi entitynk lesz ahány táblánk lesz a db-ben??????