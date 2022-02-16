package cat.copernic.msabatem.waiterme.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.databinding.FragmentLoginBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRegisterBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRolSelectorBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RolSelectorFragment : Fragment() {

    private lateinit var binding: FragmentRolSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRolSelectorBinding.inflate(inflater, container, false);

        return binding.root;
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }


}