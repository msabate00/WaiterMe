package cat.copernic.msabatem.waiterme.Waiter.Tables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.Waiter.WaiterMainFragment
import cat.copernic.msabatem.waiterme.Waiter.WaiterMainFragmentDirections

class TablesWViewAdapter(val tables: ArrayList<Table>): RecyclerView.Adapter<TablesWViewAdapter.TableHolder>(){

    lateinit var parent: ViewGroup;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        this.parent = parent;
        val layoutInflater = LayoutInflater.from(parent.context);
        return TableHolder(layoutInflater.inflate(R.layout.item_tablew, parent, false));
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
            view.findViewById<Button>(R.id.btn_Item_TableW).text = table.name;
            if(table.available){
                view.findViewById<Button>(R.id.btn_Item_TableW).isEnabled = false;
                view.findViewById<Button>(R.id.btn_Item_TableW).alpha = 0.5f;
            }else{
                view.findViewById<Button>(R.id.btn_Item_TableW).isEnabled = true;
                view.findViewById<Button>(R.id.btn_Item_TableW).alpha = 1f;
            }
            view.findViewById<Button>(R.id.btn_Item_TableW).setOnClickListener {
                openDialog(view, table, table.id ?: 0);
                //HACER LO DE MOSTRAR OTRO FRAGMENT PARA TOMAR NOTA Y TAL
            }
        }

        private fun openDialog(view: View, table: Table, id_table: Int){
            NavHostFragment.findNavController(view.findFragment()).navigate(
                WaiterMainFragmentDirections.actionWaiterMainFragmentToTableWaiterDetailFragment(
                    table
                )

            );
        }
    }
}