package cat.copernic.msabatem.waiterme.Waiter.Tables.Details

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentTableWaiterDetailBinding


class TableWaiterDetailFragment : Fragment() {

    private lateinit var binding: FragmentTableWaiterDetailBinding
    val args: TableWaiterDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentTableWaiterDetailBinding.inflate(inflater, container, false);

        binding.tvWaiterTableDetailName.text = "${getString(R.string.table)}: ${args.table.name}";


        binding.btWaiterTableDetailNewOrder.setOnClickListener {
            findNavController().navigate(TableWaiterDetailFragmentDirections.actionTableWaiterDetailFragmentToFoodSelectorFragment(args.table))
        }

        binding.btWaiterTableDetailViewOrders.setOnClickListener {
            findNavController().navigate(TableWaiterDetailFragmentDirections.actionTableWaiterDetailFragmentToOrdersViewOrderFragment(args.table))
        }


        return binding.root;
        //return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            Utils().getOrdersCountByTableId(args.table.id ?: 0, binding.tvWaiterTableDetailOrderNumber);
        }, 1000)

    }




}