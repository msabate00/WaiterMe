package cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentFoodSelectorBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentFoodSelectorReadyToSendBinding

class FoodSelector_ReadyToSendFragment : Fragment() {

    private lateinit var binding: FragmentFoodSelectorReadyToSendBinding
    val args: FoodSelector_ReadyToSendFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFoodSelectorReadyToSendBinding.inflate(inflater, container, false);

        binding.btnWaiterFoodSelectorReadyToSendFinish.setOnClickListener {
            Utils().addOrder(args.table.id!!, args.foodIds.toList(), binding.etWaiterFoodSelectorReadyToSendDetails.text.toString())
            FoodsWViewAdapter.clear_Food_Ids();
            findNavController().navigate(FoodSelector_ReadyToSendFragmentDirections.actionFoodSelectorReadyToSendFragmentToTableWaiterDetailFragment(args.table))
        }
        return binding.root;
    }


}