package cat.copernic.msabatem.waiterme.Admin.ManageFood

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils

class FoodViewAdapter(val activity1: MainActivity,val foods: ArrayList<Food>): RecyclerView.Adapter<FoodViewAdapter.FoodHolder>(){

    lateinit var parent: ViewGroup;
    val activity = activity1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {

        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return FoodHolder(layoutInflater.inflate(R.layout.item_food, parent, false), activity);
    }


    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.render(foods[position], position)
    }

    override fun getItemCount(): Int {
        return foods.size;
    }


    class FoodHolder(val view: View, val activity: MainActivity): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun render(food: Food, pos: Int){

            Utils().getFoodImage(food.id ?: 0, view.findViewById(R.id.iv_Item_Food_Image))

            if(pos%2==0){
                view.setBackgroundResource(R.color.green);
            }else{
                view.setBackgroundResource(R.color.green_2);
            }
            view.findViewById<TextView>(R.id.tv_Item_Food_name).text = food.name;
            view.findViewById<TextView>(R.id.tv_Item_Food_people).text =
                activity.getText(R.string.price).toString() +
                        " " + food.price + activity.getText(R.string.price_subfix) ;
            view.findViewById<ImageView>(R.id.iv_Item_Food_edit).setOnClickListener {
                editDialog(view,food, food.id ?: 0);
            }
            view.findViewById<ImageView>(R.id.iv_Item_Food_Image).setOnClickListener {
                editImage(view, food, food.id ?: 0);
            }

            view.findViewById<ImageView>(R.id.iv_Item_Food_Delete).setOnClickListener {
                Utils().deleteFood(food.id ?: 0);
                view.visibility = View.GONE;
            }




        }

        private fun editDialog(view: View, food: Food, id: Int){
            val builder = AlertDialog.Builder(view.context);
            builder.setTitle(R.string.edit_food);
            val input_name = EditText(view.context);
            input_name.setPadding(10);
            input_name.width = 500;
            input_name.setText(food.name);

            val input_max = EditText(view.context);
            input_max.setPadding(10);
            input_max.width = 500;
            input_max.inputType = InputType.TYPE_CLASS_NUMBER;
            input_max.setText(food.price.toString());

            val layout = LinearLayout(view.context);
            layout.orientation = LinearLayout.VERTICAL;

            val label1 = LinearLayout(view.context);
            val label2 = LinearLayout(view.context)
            val tv1 = TextView(view.context);
            tv1.text = "Name:";

            val tv2 = TextView(view.context);
            tv2.text = activity.getText(R.string.price)

            label1.addView(tv1);
            label1.addView(input_name);


            label2.addView(tv2);
            label2.addView(input_max);


            layout.addView(label1);
            layout.addView(label2);


            builder.setView(layout);
            builder.setPositiveButton(R.string.dialog_ok){
                    dialog, which ->

                Utils().updateFood(input_name.text.toString(), input_max.text.toString().toFloat(), id);
                view.findViewById<TextView>(R.id.tv_Item_Food_name).text =  input_name.text.toString();
                view.findViewById<TextView>(R.id.tv_Item_Food_people).text =
                    activity.getText(R.string.price).toString() +
                            " " +  input_max.text.toString() + activity.getText(R.string.price_subfix) ;

            }
            builder.setNegativeButton(R.string.dialog_cancel){
                    dialog, which -> dialog.cancel();
            }
            builder.show();
        }

        fun editImage(view: View, food: Food, idt: Int){
            val builder: AlertDialog.Builder? = this.let {
                AlertDialog.Builder(view.context)
            }
            builder?.setMessage(R.string.upload_picture_method)?.setPositiveButton(R.string.camera,
                DialogInterface.OnClickListener { dialog, id ->

                   activity.openCamera(1, idt.toString());
                })?.setNegativeButton(R.string.galery,
                DialogInterface.OnClickListener { dialog, id ->
                    activity.openGallery(2, idt.toString());
                })
            val dialog: AlertDialog? = builder?.create()
            builder?.create();
            builder?.show()
        }

    }
}