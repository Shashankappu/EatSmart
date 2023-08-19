package com.shashanksp.eatsmart

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),CalorieRVAdapter.CalorieItemClickInterface  {
    lateinit var itemsRV : RecyclerView
    lateinit var addFAB : FloatingActionButton
    lateinit var list: List<CalorieItems>
    lateinit var calorieRVAdapter: CalorieRVAdapter
    lateinit var calorieViewModel: CalorieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.RVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list =  ArrayList<CalorieItems>()
        calorieRVAdapter = CalorieRVAdapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = calorieRVAdapter
        val calorieRepository = CalorieRepository(CalorieDatabase(this))
        val factory = CalorieViewModelFactory(calorieRepository)
        calorieViewModel = ViewModelProvider(this,factory).get(CalorieViewModel::class.java)
        calorieViewModel.getAllCalorieItems().observe(this, Observer {
            calorieRVAdapter.list = it
            calorieRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {

            openDialog()

        }
    }

    override fun onItemClick(calorieItems: CalorieItems) {
        calorieViewModel.delete(calorieItems)
        calorieRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_SHORT).show()
    }
    fun openDialog(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.item_add_dialog)

        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnadd)
        val itemNameEdt = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        val itemCalEdt = dialog.findViewById<EditText>(R.id.idEditItemCals)


        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener{
            val itemName : String = itemNameEdt.text.toString()
            val itemQuantiy : String = itemQuantityEdt.text.toString()
            val itemCals : String = itemCalEdt.text.toString()

            val qty : Int = itemQuantiy.toInt()
            val cals : Int = itemCals.toInt()

            if(itemName.isNotEmpty() && itemCals.isNotEmpty() && itemQuantiy.isNotEmpty()){
                val items  = CalorieItems(itemName,qty,cals)
                calorieViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Added ",Toast.LENGTH_SHORT).show()
                calorieRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Please Enter all the details",Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}