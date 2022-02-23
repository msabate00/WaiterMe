package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.databinding.FragmentManageTablesBinding


class ManageTablesFragment : Fragment() {

    private lateinit var binding: FragmentManageTablesBinding


    val tables = listOf<Table>(
        Table("1"),
        Table("2"),
        Table("3"),
        Table("4"),
        Table("5"),
        Table("6"),
        Table("7"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentManageTablesBinding.inflate(inflater, container, false);
        initRecycle();
        return binding.root
    }

    fun initRecycle(){
        binding.rvManager.layoutManager = LinearLayoutManager(context);
        val adapter = TablesViewAdapter(tables);
        binding.rvManager.adapter = adapter;
    }

}