package cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.ViewOrders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageFood.Food
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.OrderItem


class OrderViewAdapter(val orders: ArrayList<OrderItem>, val fragment: Fragment): RecyclerView.Adapter<OrderViewAdapter.OrderHolder>(){

    lateinit var parent: ViewGroup;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return OrderHolder(layoutInflater.inflate(R.layout.item_order, parent, false), fragment);
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.render(orders[position], position)
    }


    override fun getItemCount(): Int {
        return orders.size;
    }


    class OrderHolder(val view: View, val f: Fragment): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun render(order: OrderItem, pos: Int){

            view.findViewById<TextView>(R.id.tv_Item_Order_NumberAsigned).text = pos.toString();

            for(id: Int in order.foods_id!!){
                Utils().getFoodById(id, view.findViewById(R.id.tv_Item_Order_ListFood))
            }



            /*Utils().getFoodImage(order.id ?: 0, view.findViewById(R.id.iv_foodSelector_item_image))
            view.findViewById<TextView>(R.id.tv_foodSelector_item_name).text = order.name;
            if(!order.available){
                view.findViewById<ImageView>(R.id.iv_foodSelector_not).alpha = 0.7f;
            }else{
                view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setOnClickListener {
                    openDialog(view, order, f);
                }
            }*/
        }

        private fun openDialog(view: View, food: Food, f: Fragment){
            /*if(food_ids_selected.size > 0 && food_ids_selected.contains(food.id)){
                view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setBackgroundColor(view.resources.getColor(
                    R.color.orange));
                food_ids_selected.remove(food.id);
            }else{
                view.findViewById<ConstraintLayout>(R.id.cl_foodw_item).setBackgroundColor(view.resources.getColor(
                    R.color.green));
                food_ids_selected.add(food.id!!);
            }
            f.view?.findViewById<Button>(R.id.btn_FoodSelector_send)?.isEnabled = food_ids_selected.size != 0
*/
        }
    }
}