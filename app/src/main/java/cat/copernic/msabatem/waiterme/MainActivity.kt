package cat.copernic.msabatem.waiterme

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.google.firebase.auth.FirebaseAuth
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var currentPhotoPath: String
    lateinit var currentPhotoName: String
    lateinit var currentPhotoIDName: String
    lateinit var currentPhotoURI: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    public fun openGallery(code: Int = 2, name: String = "0") {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        currentPhotoIDName = name;
        startActivityForResult(gallery, code)
    }

    public fun openCamera(code: Int = 1, name: String = "0") {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "cat.copernic.msabatem.waiterme",
                        it
                    )
                    currentPhotoIDName = name;
                    currentPhotoURI = photoURI;
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, code)

                }
            }
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val prefix = "JPEG_${timeStamp}_";
        return File.createTempFile(
            prefix, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            currentPhotoName = prefix + ".jpg"
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            var storageRef = Utils().getStorageRef().child("food");

            //if (File(currentPhotoPath).exists()) {
            var bitmaps =
                MediaStore.Images.Media.getBitmap(contentResolver, currentPhotoURI)
            var outba = ByteArrayOutputStream();
            if (bitmaps != null) {
                bitmaps.compress(
                    Bitmap.CompressFormat.JPEG,
                    70,
                    outba
                );
                val dadesbytes = outba.toByteArray();

                val pathReferenceSubir =
                    storageRef.child("/id_$currentPhotoIDName.jpg")

                pathReferenceSubir.putBytes(dadesbytes);

                Toast.makeText(
                    applicationContext,
                    R.string.has_been_uploaded_successfully,
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(applicationContext, R.string.operation_was_canceled, Toast.LENGTH_SHORT)
                    .show()
            }
        } else if (requestCode == 2) {
            var storageRef = Utils().getStorageRef().child("food");
            if (data != null) {

                var bitmaps =
                    MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData())
                var outba = ByteArrayOutputStream();


                bitmaps.compress(
                    Bitmap.CompressFormat.JPEG,
                    70,
                    outba
                );
                val dadesbytes = outba.toByteArray();

                val pathReferenceSubir =
                    storageRef.child("/id_$currentPhotoIDName.jpg")
                pathReferenceSubir.putBytes(dadesbytes);
                Toast.makeText(
                    applicationContext,
                    R.string.has_been_uploaded_successfully,
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(applicationContext, R.string.operation_was_canceled, Toast.LENGTH_SHORT)
                    .show()
            }

        }



    }

}