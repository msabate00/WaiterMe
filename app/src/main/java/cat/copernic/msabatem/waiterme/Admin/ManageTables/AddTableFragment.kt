package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentAddTableBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentManageTablesBinding


class AddTableFragment : Fragment() {

    private lateinit var binding: FragmentAddTableBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTableBinding.inflate(inflater, container, false);
        binding.btAddTableCancel.setOnClickListener {
            findNavController().navigate(AddTableFragmentDirections.actionAddTableFragmentToManageTablesFragment());
        }
        binding.btAddTableAdd.setOnClickListener {
            if(binding.etAddTableName.text.toString() == "" || binding.etAddTableName.text.toString() == null){
                Toast.makeText(context, getString(R.string.error_name_empty), Toast.LENGTH_SHORT).show()
            }else if (binding.etAddTableMax.text.toString() == "" || binding.etAddTableMax.text.toString() == null){
                Toast.makeText(context, getString(R.string.error_max_people_empty), Toast.LENGTH_SHORT).show()
            }else{
                Utils().addTable(binding.etAddTableName.text.toString(), binding.etAddTableMax.text.toString().toInt());
                Toast.makeText(context, getString(R.string.added_table) + " " + binding.etAddTableName.text.toString(), Toast.LENGTH_SHORT).show()
                binding.etAddTableMax.text.clear();
                binding.etAddTableName.text.clear();
            }

        }


        return binding.root
    }


}