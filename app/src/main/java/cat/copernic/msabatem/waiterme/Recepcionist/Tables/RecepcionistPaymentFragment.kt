package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        binding.btnRecepcionistPaymentGoback.setOnClickListener {
            findNavController().popBackStack();
        }
        binding.btnRecepcionistPaymentPay.setOnClickListener{
            Utils().setfinishedAllOrdersFromTable(args.table.id!!, true);
            Utils().editAvaileableTable(args.table.id!!, true);
            Toast.makeText(context, getString(R.string.msg_paid), Toast.LENGTH_SHORT).show()
            findNavController().popBackStack();
        }


        return binding.root
    }



}