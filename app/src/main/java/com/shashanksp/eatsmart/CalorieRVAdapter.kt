package com.shashanksp.eatsmart


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalorieRVAdapter(
    var list: List<CalorieItems>,
    val calorieItemClickInterface: CalorieItemClickInterface
) :RecyclerView.Adapter<CalorieRVAdapter.CalorieViewHolder>(){

    inner class CalorieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val CalorieTV = itemView.findViewById<TextView>(R.id.idTVCals)
        var  TotalCalsTV = itemView.findViewById<TextView>(R.id.idTVTotalCal)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    interface CalorieItemClickInterface{
        fun onItemClick(calorieItems: CalorieItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalorieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calorie_rv_items,parent,false)
        return CalorieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalorieViewHolder, position: Int) {
        holder.nameTV.text = list[position].itemName
        holder.quantityTV.text = list[position].itemQuantity.toString()
        holder.CalorieTV.text = list[position].itemCalories.toString()

        val itemTotal : Int = list[position].itemCalories * list.get(position).itemQuantity

        holder.TotalCalsTV.text = "${itemTotal} Cals"

        holder.deleteIV.setOnClickListener{
            calorieItemClickInterface.onItemClick(list[position])
        }
    }
}