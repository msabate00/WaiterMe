package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils

class TablesRViewAdapter(val tables: ArrayList<Table>): RecyclerView.Adapter<TablesRViewAdapter.TableHolder>(){

    lateinit var parent: ViewGroup;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return TableHolder(layoutInflater.inflate(R.layout.item_tabler, parent, false));
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
            view.findViewById<Button>(R.id.btn_Item_TableR).text = table.name;
            if(!table.available){
                view.findViewById<Button>(R.id.btn_Item_TableR).isEnabled = false;
                view.findViewById<Button>(R.id.btn_Item_TableR).alpha = 0.5f;
            }
            view.findViewById<Button>(R.id.btn_Item_TableR).setOnClickListener {
                openDialog(view, table, table.id ?: 0);
            }
        }

        private fun openDialog(view: View, table: Table, id: Int){
            val builder: AlertDialog.Builder = this.let {
                AlertDialog.Builder(view.context)
            }
            builder.setMessage(R.string.take_up_the_table)?.setPositiveButton(R.string.take_it,
                DialogInterface.OnClickListener { dialog, id ->
                    view.findViewById<Button>(R.id.btn_Item_TableR).isEnabled = false;
                    view.findViewById<Button>(R.id.btn_Item_TableR).alpha = 0.5f;
                    Utils().editAvaileableTable(id, false);
                })?.setNegativeButton(R.string.dialog_cancel,
                DialogInterface.OnClickListener { dialog, id ->

                })
            val dialog: AlertDialog? = builder.create()
            builder.create();
            builder.show();


        }



    }


}