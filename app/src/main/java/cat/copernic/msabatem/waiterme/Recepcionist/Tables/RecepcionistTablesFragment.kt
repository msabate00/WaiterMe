package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentManageTablesBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRecepcionistTablesBinding


class RecepcionistTablesFragment : Fragment() {

    private lateinit var binding: FragmentRecepcionistTablesBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecepcionistTablesBinding.inflate(inflater, container, false);
        initRecycle();



        return binding.root
    }

    fun initRecycle(){
        binding.rvRecepcionistTables.layoutManager = GridLayoutManager(context, 4);
        Utils().getTables(requireParentFragment(), binding.rvRecepcionistTables, Utils.TABLE_RECEPTIONIS)

    }

}