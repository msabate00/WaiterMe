package cat.copernic.msabatem.waiterme.Recepcionist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Recepcionist.ReceptionistMainFragmentDirections
import cat.copernic.msabatem.waiterme.databinding.FragmentAdminMainBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentReceptionistMainBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRegisterBinding


class ReceptionistMainFragment : Fragment() {

    private lateinit var binding: FragmentReceptionistMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentReceptionistMainBinding.inflate(inflater, container, false);


        binding.btReceptionistTake.setOnClickListener {
            findNavController().navigate(ReceptionistMainFragmentDirections.actionReceptionistMainFragmentToRecepcionistTablesFragment());
        }
        binding.btReceptionistPay.setOnClickListener {
            findNavController().navigate(ReceptionistMainFragmentDirections.actionReceptionistMainFragmentToRecepcionistTablesFragment());
        }

        return binding.root;
        //return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }


}