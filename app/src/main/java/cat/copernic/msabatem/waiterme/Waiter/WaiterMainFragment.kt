package cat.copernic.msabatem.waiterme.Waiter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Recepcionist.ReceptionistMainFragmentDirections
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentReceptionistMainBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentWaiterMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WaiterMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WaiterMainFragment: Fragment() {

    private lateinit var binding: FragmentWaiterMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentWaiterMainBinding.inflate(inflater, container, false);

        initRecycle();

        return binding.root;
        //return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }

    fun initRecycle(){
        binding.rvWaiterTables.layoutManager = GridLayoutManager(context, 4);
        Utils().getTables(requireParentFragment(), binding.rvWaiterTables, Utils.TABLE_WAITER)
    }


}