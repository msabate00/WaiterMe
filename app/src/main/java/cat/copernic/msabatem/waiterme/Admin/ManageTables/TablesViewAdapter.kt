package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.opengl.Visibility
import android.text.InputType.TYPE_CLASS_NUMBER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils

class TablesViewAdapter(val tables: ArrayList<Table>): RecyclerView.Adapter<TablesViewAdapter.TableHolder>(){

    lateinit var parent: ViewGroup;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return TableHolder(layoutInflater.inflate(R.layout.item_table, parent, false));
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.render(tables[position], position)
    }

    override fun getItemCount(): Int {
        return tables.size;
    }


    class TableHolder(val view: View): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun render(table: Table, pos: Int){
            if(pos%2==0){
                view.setBackgroundResource(R.color.green);
            }else{
                view.setBackgroundResource(R.color.green_2);
            }
            view.findViewById<TextView>(R.id.tv_Item_Table_name).text = table.name;
            view.findViewById<ImageView>(R.id.iv_Item_Table_edit).setOnClickListener {
                editDialog(view, table.id ?: 0);
            }
            view.findViewById<ImageView>(R.id.iv_Item_Table_Delete).setOnClickListener {
                Utils().deleteTable(table.id ?: 0);
                view.visibility = View.GONE;
            }
        }

        private fun editDialog(view: View, id: Int){
            val builder = AlertDialog.Builder(view.context);
            builder.setTitle(R.string.edit_table);
            val input_name = EditText(view.context);
            input_name.setPadding(10);
            input_name.width = 500;

            val input_max = EditText(view.context);
            input_max.setPadding(10);
            input_max.width = 500;
            input_max.inputType = TYPE_CLASS_NUMBER;

            val layout = LinearLayout(view.context);
            layout.orientation = LinearLayout.VERTICAL;

            val label1 = LinearLayout(view.context);
            val label2 = LinearLayout(view.context)
            val tv1 = TextView(view.context);
            tv1.text = "Name:";

            val tv2 = TextView(view.context);
            tv2.text = "Max Pepl."

            label1.addView(tv1);
            label1.addView(input_name);


            label2.addView(tv2);
            label2.addView(input_max);


            layout.addView(label1);
            layout.addView(label2);


            builder.setView(layout);
            builder.setPositiveButton(R.string.dialog_ok){
                dialog, which ->

                Utils().updateTable(input_name.text.toString(), input_max.text.toString().toInt(), id);
                view.findViewById<TextView>(R.id.tv_Item_Table_name).text = input_name.text.toString();
            }
            builder.setNegativeButton(R.string.dialog_cancel){
                dialog, which -> dialog.cancel();
            }
            builder.show();


        }



    }


}