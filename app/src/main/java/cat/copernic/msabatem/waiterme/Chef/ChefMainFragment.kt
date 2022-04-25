package cat.copernic.msabatem.waiterme.Chef

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentChefMainBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentWaiterMainBinding


class ChefMainFragment : Fragment() {

    private lateinit var binding: FragmentChefMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChefMainBinding.inflate(inflater, container, false);
        initRecycle();

        binding.btnChefOrdersViewBack.setOnClickListener {
            findNavController().popBackStack();
        }


       return binding.root;
    }

    fun initRecycle(){
        binding.rvChefOrdersViewFoods.layoutManager = LinearLayoutManager(context);
        //Utils().getFood(requireParentFragment(), binding.rvFoodSelectorFoods, activity as MainActivity, Utils.FOOD_WAITER)
        Utils().getAllFoodsAllOrders(binding.rvChefOrdersViewFoods,  this)
    }

}