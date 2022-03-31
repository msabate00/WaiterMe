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
import cat.copernic.msabatem.waiterme.databinding.FragmentWaiterMainBinding


class FoodSelectorFragment : Fragment() {

    private lateinit var binding: FragmentFoodSelectorBinding
    val args: FoodSelectorFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFoodSelectorBinding.inflate(inflater, container, false);

        binding.btnFoodSelectorSend.setOnClickListener {
            findNavController().navigate(FoodSelectorFragmentDirections.actionFoodSelectorFragmentToFoodSelectorReadyToSendFragment(args.table, FoodsWViewAdapter.food_ids_selected.toIntArray()))
        }

        initRecycle();

        return binding.root;
    }

    fun initRecycle(){
        binding.rvFoodSelectorFoods.layoutManager = GridLayoutManager(context, 3);
        Utils().getFood(requireParentFragment(), binding.rvFoodSelectorFoods, activity as MainActivity, Utils.FOOD_WAITER)
    }


}