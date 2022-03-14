package cat.copernic.msabatem.waiterme.Admin.ManageFood

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.waiterme.Admin.ManageFood.AddFoodFragmentDirections
import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R
import cat.copernic.msabatem.waiterme.Utils
import cat.copernic.msabatem.waiterme.databinding.FragmentAddFoodBinding
import com.google.firebase.auth.FirebaseAuth
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddFoodFragment : Fragment() {

    private lateinit var binding: FragmentAddFoodBinding

    private lateinit var currentPhotoPath: String;
    private lateinit var currentPhotoURI: Uri;
    private lateinit var currentPhotoName: String;





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
                Utils().addFood(binding.etAddFoodName.text.toString(), binding.etAddFoodMax.text.toString().toFloat());
                Toast.makeText(context, "Se añadio la siguiente comida: " + binding.etAddFoodName.text.toString(), Toast.LENGTH_SHORT).show()
                binding.etAddFoodMax.text.clear();
                binding.etAddFoodName.text.clear();
            }

        }
        return binding.root
    }
}