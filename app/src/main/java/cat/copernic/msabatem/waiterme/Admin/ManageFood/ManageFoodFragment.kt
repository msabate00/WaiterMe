package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentManageFoodBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentManageTablesBinding


class ManageFoodFragment : Fragment() {

    private lateinit var binding: FragmentManageFoodBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentManageFoodBinding.inflate(inflater, container, false);
        initRecycle();
        binding.btManageFoodAdd.setOnClickListener {
            findNavController().navigate(ManageFoodFragmentDirections.actionManageFoodFragmentToAddFoodFragment())
        }


        return binding.root
    }

    fun initRecycle(){
        binding.rvFoodAdminManager.layoutManager = LinearLayoutManager(context);
        Utils().getFood(requireParentFragment(),binding.rvFoodAdminManager, activity as MainActivity)

    }

}