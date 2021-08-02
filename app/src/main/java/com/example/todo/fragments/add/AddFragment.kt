package com.example.todo.fragments.add


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.models.ToDoData
import com.example.todo.data.viewmodel.ToDoViewModel
import com.example.todo.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels() //ezek egy objektummodellek. Ezek alapjan tudom majd az osztalyban levo dolgokat elerni
    private val mSharedViewModel : SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)

        view.priorities_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() { //db-be rakja a dolgokat
        val mTitle = title_et.text.toString()                                   //amit a text fieldbe beleirtak stringe konvertaljuk
        val mPriority = priorities_spinner.selectedItem.toString()              //spinner miatt kell a selectedItem
        val mDescription = description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)               //ez egy boolean lesz
        if(validation){
            //if true insert data
            val newData = ToDoData(
                0,  //room automatikusan noveli majd ezt az erteket
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Succesfully added!", Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }



}