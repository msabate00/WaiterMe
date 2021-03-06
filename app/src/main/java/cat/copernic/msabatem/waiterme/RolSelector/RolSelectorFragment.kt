package cat.copernic.msabatem.waiterme.Login

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentRolSelectorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


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
            adminDialog();
        }

        binding.btRolSelectorClose.setOnClickListener {
            var auth = Firebase.auth;
            auth.signOut();
            findNavController().navigate(RolSelectorFragmentDirections.actionRolSelectorFragmentToLoginFragment());
        }

        binding.btRolSelectorReceptionist.setOnClickListener {
            findNavController().navigate(RolSelectorFragmentDirections.actionRolSelectorFragmentToReceptionistMainFragment());
        }
        binding.btRolSelectorWaiter.setOnClickListener {
            findNavController().navigate(RolSelectorFragmentDirections.actionRolSelectorFragmentToWaiterMainFragment2())
        }

        binding.btRolSelectorChef.setOnClickListener {
            findNavController().navigate(RolSelectorFragmentDirections.actionRolSelectorFragmentToChefMainFragment())
        }


        return binding.root;
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }


    private fun adminDialog(){
        var actualPin = "";
        builder = AlertDialog.Builder(context)
        builder.setTitle("Admin PIN")
        val input = EditText(context);
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(4);
        input.filters = filterArray;
        input.inputType = InputType.TYPE_CLASS_NUMBER;
        builder.setView(input);
        builder.setPositiveButton(getString(R.string.dialog_ok)) {
                dialog, which ->
            actualPin = input.text.toString() ;
            val ref = Utils().getDatabase().getReference("locals/" + Utils().getAuth().uid + "/super_pin")
            ref.get().addOnSuccessListener {
                if(it.value.toString() == Utils().md5(actualPin)) {
                    findNavController().navigate(RolSelectorFragmentDirections.actionRolSelectorFragmentToAdminMainFragment())
                }else{
                    Toast.makeText(context, getString(R.string.wrong_pin), Toast.LENGTH_SHORT).show()
                }
            }

        }

        builder.setNegativeButton(
            getString(R.string.dialog_cancel)
        ) { dialog, which -> dialog.cancel() }
        builder.show()

    }


}