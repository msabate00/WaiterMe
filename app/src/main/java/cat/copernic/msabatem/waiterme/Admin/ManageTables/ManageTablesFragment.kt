package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.msabatem.waiterme.R
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

        return binding.root
    }
}