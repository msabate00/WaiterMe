package cat.copernic.msabatem.waiterme.Waiter.Tables.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentTableWaiterDetailBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentWaiterMainBinding

class TableWaiterDetailFragment : Fragment() {

    private lateinit var binding: FragmentTableWaiterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentTableWaiterDetailBinding.inflate(inflater, container, false);



        return binding.root;
        //return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }




}