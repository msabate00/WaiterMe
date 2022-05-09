package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentRecepcionistPaymentBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRecepcionistTablesBinding


class RecepcionistPaymentFragment  : Fragment() {

    private lateinit var binding: FragmentRecepcionistPaymentBinding
    val args: RecepcionistPaymentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecepcionistPaymentBinding.inflate(inflater, container, false);


        Utils().getAllPricesFromTable(args.table.id!!, binding.tvReceptionistPaymentViewCost)



        return binding.root
    }



}