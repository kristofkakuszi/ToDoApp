package com.example.todo.data

import androidx.room.TypeConverter
import com.example.todo.data.models.Priority

class Converter {

    /**
     * 20 vidi egy converter
     * ez azt csinalja hogy mi keszitettunk egy priority classt ami low, medium, high értéket tartalmaz.
     * Viszont az ertekek a db-ben csak primitv tipusok lehetnek.
     * Ezért készítünk egy converter osztályt is ami db-be iraskor string-e alakitja db-ből valo olvasaskor meg visszaalakitja priority típussa
     */

    @TypeConverter //ezzel tudja a room hogy konvertalas lesz
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String) : Priority {
        return Priority.valueOf(priority)
    }
}