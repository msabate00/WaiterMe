package cat.copernic.msabatem.waiterme.Chef

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageFood.Food
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils



class ChefFoodViewAdapter(val orders: ArrayList<Food>, val fragment: Fragment): RecyclerView.Adapter<ChefFoodViewAdapter.FoodHolder>(){

    lateinit var parent: ViewGroup;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return FoodHolder(layoutInflater.inflate(R.layout.item_foodc, parent, false), fragment);
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.render(orders[position], position)
    }


    override fun getItemCount(): Int {
        return orders.size;
    }


    class FoodHolder(val view: View, val f: Fragment): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun render(food: Food, pos: Int){

            Utils().getFoodImage(food.id ?: 0, view.findViewById(R.id.iv_Food_Item_Chef));
            view.findViewById<TextView>(R.id.tv_Item_Food_name_Cheff).text = food.name;


            /*view.findViewById<TextView>(R.id.tv_Item_Order_NumberAsigned).text = pos.toString();
            view.findViewById<TextView>(R.id.tv_Item_Order_ListFood).text = "";
            for(id: Int in order.foods_id!!){
                Utils().getFoodById(id, view.findViewById(R.id.tv_Item_Order_ListFood))
            }

            view.findViewById<Button>(R.id.btn_Item_Order_Cancel).setOnClickListener {
                Utils().deleteOrderById(order.id!!);
                view.visibility = View.GONE;
            }*/

        }
    }
}