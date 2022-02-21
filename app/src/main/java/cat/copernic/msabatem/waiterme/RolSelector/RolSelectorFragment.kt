package cat.copernic.msabatem.waiterme.Login

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import cat.copernic.msabatem.waiterme.databinding.FragmentRolSelectorBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RolSelectorFragment : Fragment() {

    private lateinit var binding: FragmentRolSelectorBinding
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRolSelectorBinding.inflate(inflater, container, false);


        binding.btRolSelectorAdmin.setOnClickListener {
            builder = AlertDialog.Builder(context)
            builder.setTitle("Title")
            val input = EditText(context);
            input.inputType = InputType.TYPE_NUMBER_FLAG_SIGNED;
            input.filters[0] = InputFilter.LengthFilter(4);
            builder.setView(input);
        }

        return binding.root;
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }


}