package com.example.todo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )

        /**
         * val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
         * return MyViewHolder(view)
         *
         * ennek a kodnak a fenti return MyViewHolder(stb...)-jét nevezzük kb binding-nak
         */
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.title_txt.text = dataList[position].title // a datalist ToDoData tipusu es azoknak a title-jet szeretnek itt megkapni es ezt berkajuk a title_txt-be
        holder.itemView.description_txt.text = dataList[position].description

        //we pass tododata object from listframgment to updatefragment
        holder.itemView.row_background.setOnClickListener{

            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            //holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment) ez volt itt es ez helyett R.id.action_listFragment_to_updateFragment -> hasznaljuk
            //az action ami egy parcalable i guess
            holder.itemView.findNavController().navigate(action)
        }

        val priority = dataList[position].priority
        when (priority) {
            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.yellow)
            )
            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoData>) { //set data nem pontosan ertheto max megkérdezni a végén
        this.dataList =
            toDoData //basically we want to set this tododata from the parameters to our tododata from our listadapter and we want to notify our recyclerview about
        // those changes
        notifyDataSetChanged()
    }
    //this setData function will be used from our list fragment when we are observing livedata object
    // so we will see lulululul


    /**
     * ez gyakorlatilag egy ToDoData lista
     */

}