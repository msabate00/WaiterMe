package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Recepcionist.ReceptionistMainFragmentDirections
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.Waiter.WaiterMainFragmentDirections

class TablesRTViewAdapter(val tables: ArrayList<Table>): RecyclerView.Adapter<TablesRTViewAdapter.TableHolder>(){

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
            if(table.available){
                view.findViewById<Button>(R.id.btn_Item_TableR).isEnabled = false;
                view.findViewById<Button>(R.id.btn_Item_TableR).alpha = 0.5f;
            }
            view.findViewById<Button>(R.id.btn_Item_TableR).setOnClickListener {
                openDialog(view, table);
            }
        }

        private fun openDialog(view: View, table: Table){

            NavHostFragment.findNavController(view.findFragment()).navigate(
                RecepcionistTablesFragmentDirections.actionRecepcionistTablesFragmentToRecepcionistPaymentFragment(
                    table
                )
            );

           // Utils().getAllPricesFromTable(table.id!!, view.findViewById(R.id.tv_Receptionist_Payment_View_Cost));
        }



    }


}