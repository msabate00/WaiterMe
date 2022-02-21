package cat.copernic.msabatem.waiterme.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.databinding.FragmentAdminMainBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRegisterBinding


class AdminMainFragment : Fragment() {

    private lateinit var binding: FragmentAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAdminMainBinding.inflate(inflater, container, false);

        return binding.root;
       //return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }


}