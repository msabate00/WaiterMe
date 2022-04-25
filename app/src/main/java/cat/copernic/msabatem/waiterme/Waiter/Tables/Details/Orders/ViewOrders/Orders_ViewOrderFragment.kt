package cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.ViewOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.FoodSelector_ReadyToSendFragmentArgs
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.FoodSelector_ReadyToSendFragmentDirections
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.FoodsWViewAdapter
import cat.copernic.msabatem.waiterme.databinding.FragmentFoodSelectorReadyToSendBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentOrdersViewOrderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Orders_ViewOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Orders_ViewOrderFragment  : Fragment() {

    private lateinit var binding: FragmentOrdersViewOrderBinding
    val args: Orders_ViewOrderFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOrdersViewOrderBinding.inflate(inflater, container, false);

        initRecycle();

        binding.btnOrdersViewBack.setOnClickListener {
            findNavController().popBackStack();
        }

        return binding.root;
    }

    private fun initRecycle(){
        binding.rvOrdersViewFoods.layoutManager = LinearLayoutManager(context);
        //Utils().getFood(requireParentFragment(), binding.rvFoodSelectorFoods, activity as MainActivity, Utils.FOOD_WAITER)
        Utils().getOrders(binding.rvOrdersViewFoods, args.table.id ?: 0, this)
    }


}