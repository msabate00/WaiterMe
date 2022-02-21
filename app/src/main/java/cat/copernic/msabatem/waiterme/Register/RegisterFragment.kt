package cat.copernic.msabatem.waiterme.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentLoginBinding
import cat.copernic.msabatem.waiterme.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.btRegisterGoBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
        }
        binding.btRegisterSingUp.setOnClickListener {
            if(binding.etRegisterEmail.text.toString() != "" && binding.etRegisterPassword.text.toString() != ""){
                if(binding.etRegisterConfirmPassword.text.toString() == binding.etRegisterPassword.text.toString()){
                    if(binding.etRegisterPassword.text.toString().length >=6 ){
                        //if(binding.etRegister)
                        createAccount();
                    }else{
                        Toast.makeText(context, getString(R.string.pass_too_short), Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, getString(R.string.pass_not_match), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context,getString(R.string.empty_values), Toast.LENGTH_SHORT).show();
            }

        }


        return binding.root;
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
    }


    private fun createAccount() {
        // [START create_user_with_email]
        val email = binding.etRegisterEmail.text.toString();
        val password = binding.etRegisterPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AYUDA", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)


                    val ref = Utils().getDatabase().getReference("locals/" + user!!.uid + "/super_pin");
                    ref.setValue(binding.etRegisterSuperAdminPass.text.toString());

                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToRolSelectorFragment())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AYUDA", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, getString(R.string.already_user),
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    fun updateUI(user: FirebaseUser?){

    }


}