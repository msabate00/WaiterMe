package cat.copernic.msabatem.waiterme.Waiter.Tables

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils

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
            val builder: AlertDialog.Builder = this.let {
                AlertDialog.Builder(view.context)
            }
            builder.setMessage(R.string.take_up_the_table)?.setPositiveButton(
                R.string.take_it,
                DialogInterface.OnClickListener { dialog, id ->
                    view.findViewById<Button>(R.id.btn_Item_TableW).isEnabled = false;
                    view.findViewById<Button>(R.id.btn_Item_TableW).alpha = 0.5f;
                    Utils().editAvaileableTable(id_table, false);
                })?.setNegativeButton(
                R.string.dialog_cancel,
                DialogInterface.OnClickListener { dialog, id ->

                })
            val dialog: AlertDialog? = builder.create()
            builder.create();
            builder.show();


        }



    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getBindingAdapterPosition] which
     * will have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */



}