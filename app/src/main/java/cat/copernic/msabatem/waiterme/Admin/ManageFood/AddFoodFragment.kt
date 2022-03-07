package cat.copernic.msabatem.waiterme.Admin.ManageFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.waiterme.Admin.ManageFoods.AddFoodFragmentDirections
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentAddFoodBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFoodFragment : Fragment() {

    private lateinit var binding: FragmentAddFoodBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddFoodBinding.inflate(inflater, container, false);
        binding.btAddFoodCancel.setOnClickListener {
            findNavController().navigate(AddFoodFragmentDirections.actionAddFoodFragmentToManageFoodFragment());
        }
        binding.btAddFoodAdd.setOnClickListener {
            if(binding.etAddFoodName.text.toString() == "" || binding.etAddFoodName.text.toString() == null){
                Toast.makeText(context, getString(R.string.error_name_empty), Toast.LENGTH_SHORT).show()
            }else if (binding.etAddFoodMax.text.toString() == "" || binding.etAddFoodMax.text.toString() == null){
                Toast.makeText(context, getString(R.string.error_max_people_empty), Toast.LENGTH_SHORT).show()
            }else{
                Utils().addFood(binding.etAddFoodName.text.toString(), binding.etAddFoodMax.text.toString().toInt());
                Toast.makeText(context, getString(R.string.added_Food) + " " + binding.etAddFoodName.text.toString(), Toast.LENGTH_SHORT).show()
                binding.etAddFoodMax.text.clear();
                binding.etAddFoodName.text.clear();
            }

        }


        return binding.root
    }


}