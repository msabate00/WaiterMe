package cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageFood.Food
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.Waiter.Tables.TablesWViewAdapter

class FoodsWViewAdapter(val foods: ArrayList<Food>): RecyclerView.Adapter<FoodsWViewAdapter.FoodHolder>(){

    lateinit var parent: ViewGroup;

    companion object{
        var food_ids_selected = ArrayList<Int>();
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return FoodHolder(layoutInflater.inflate(R.layout.item_foodw, parent, false));
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.render(foods[position], position)
    }


    override fun getItemCount(): Int {
        return foods.size;
    }


    class FoodHolder(val view: View): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun render(food: Food, pos: Int){
            Utils().getFoodImage(food.id ?: 0, view.findViewById(R.id.iv_foodSelector_item_image))
            view.findViewById<TextView>(R.id.tv_foodSelector_item_name).text = food.name;
            if(!food.available){
                view.findViewById<ConstraintLayout>(R.id.iv_foodSelector_not).alpha = 0.7f;

            }
            view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setOnClickListener {
                openDialog(view, food);
                //HACER LO DE MOSTRAR OTRO FRAGMENT PARA TOMAR NOTA Y TAL
            }
        }

        private fun openDialog(view: View, food: Food){
            if(food_ids_selected.size > 0 && food_ids_selected.contains(food.id)){
                view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setBackgroundColor(view.resources.getColor(R.color.orange));
                food_ids_selected.remove(food.id);
            }else{
                view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setBackgroundColor(view.resources.getColor(R.color.green));
                food_ids_selected.add(food.id!!);
            }

        }
    }
}