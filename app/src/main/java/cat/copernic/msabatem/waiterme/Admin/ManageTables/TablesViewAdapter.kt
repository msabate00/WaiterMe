package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginLeft
import androidx.core.view.setPadding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Login.RolSelectorFragmentDirections
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class TablesViewAdapter(val tables: List<Table>): RecyclerView.Adapter<TablesViewAdapter.TableHolder>(){

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
                editDialog(view);
            }
        }

        private fun editDialog(view: View){
            val builder = AlertDialog.Builder(view.context);
            builder.setTitle(R.string.edit_table);
            val input = EditText(view.context);
            input.setPadding(10);
            builder.setView(input);
            builder.setPositiveButton(R.string.dialog_ok){
                dialog, which ->
                //TODO("QUE SE CAMBIA EL NOMBRE EN LA BBDD")
                view.findViewById<TextView>(R.id.tv_Item_Table_name).text = input.text.toString();
            }
            builder.setNegativeButton(R.string.dialog_cancel){
                dialog, which -> dialog.cancel();
            }
            builder.show();


        }



    }


}