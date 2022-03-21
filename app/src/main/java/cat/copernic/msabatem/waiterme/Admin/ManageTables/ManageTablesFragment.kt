package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentManageTablesBinding


class ManageTablesFragment : Fragment() {

    private lateinit var binding: FragmentManageTablesBinding





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
        binding.btManageTablesAdd.setOnClickListener {
            findNavController().navigate(ManageTablesFragmentDirections.actionManageTablesFragmentToAddTableFragment())
        }


        return binding.root
    }

    fun initRecycle(){
        binding.rvManager.layoutManager = LinearLayoutManager(context);
        Utils().getTables(requireParentFragment(), binding.rvManager, Utils.TABLE_ADMIN)

    }

}