package cat.copernic.msabatem.waiterme

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageFood.Food
import cat.copernic.msabatem.waiterme.Admin.ManageFood.FoodViewAdapter
import cat.copernic.msabatem.waiterme.Admin.ManageTables.ManageTablesFragment
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.Admin.ManageTables.TablesViewAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.FirebaseStorageKtxRegistrar
import java.io.File
import java.io.IOException
import java.math.BigInteger
import java.net.URI
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

class Utils {
    private val database = FirebaseDatabase.getInstance("https://waiterme-default-rtdb.europe-west1.firebasedatabase.app/");
    private val storage = FirebaseStorage.getInstance("gs://waiterme.appspot.com");


    private val auth = Firebase.auth;
    private val databaseRef = database.reference;
    private val storageRef = storage.getReference().child("/locals/${auth.uid}/images/")


    fun getStorage(): FirebaseStorage {
        return storage;
    }
    fun getStorageRef(): StorageReference {
        return storageRef;
    }

    fun getDatabase(): FirebaseDatabase {
        return database;
    }
    fun getAuth(): FirebaseAuth {
        return auth;
    }
    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }



    fun getTables(f: Fragment, rv: RecyclerView){

        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var tables: ArrayList<Table> = arrayListOf<Table>();

        ref.child("tables").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                if(snapshot.exists()){
                    for (tableSnapshot in snapshot.children) {
                        var table = tableSnapshot.getValue<Table>();
                        table!!.id = tableSnapshot.key!!.toInt();
                        tables.add(table!!);
                        rv.adapter = TablesViewAdapter(tables);
                    }

                    Log.i("AYUDA","ANTES" + tables[0].name)
                    rv.adapter = TablesViewAdapter(tables);
                    Log.i("AYUDA","DESPUES")
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: " , "Error: "+ "code " + error.code + ", " + error.details );
            }
        });

    }
    fun addTable(name: String, max_people: Int){
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        ref.child("tables").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val table = Table(name, max_people);
                if(snapshot.exists()){
                    var count = 0;
                    for(snapshotData in snapshot.children){
                        count = max(count, snapshotData.key!!.toInt() ?: 0);
                    }
                    count++;
                    ref.child("tables").child(count.toString()).setValue(table);

                }else{
                    ref.child("tables").child("1").setValue(table);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun updateTable(name: String, max_people: Int, id: Int){
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    ref.child("name").setValue(name);
                    ref.child("max_people").setValue(max_people);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun deleteTable(id: Int){
        databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString()).removeValue();
    }


    fun editAvaileableTable(id: Int, b: Boolean){
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    ref.child("available").setValue(b);

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    fun getFood(f: Fragment, rv: RecyclerView){

        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var foods: ArrayList<Food> = arrayListOf<Food>();

        ref.child("foods").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                if(snapshot.exists()){
                    for (foodSnapshot in snapshot.children) {
                        var food = foodSnapshot.getValue<Food>();
                        food!!.id = foodSnapshot.key!!.toInt();
                        foods.add(food!!);
                        rv.adapter = FoodViewAdapter(foods);
                    }


                    rv.adapter = FoodViewAdapter(foods);

                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: " , "Error: "+ "code " + error.code + ", " + error.details );
            }
        });

    }
    fun addFood(name: String, price: Float){
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        ref.child("foods").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val food = Food(name, price);
                if(snapshot.exists()){
                    var count = 0;
                    for(snapshotData in snapshot.children){
                        count = max(count, snapshotData.key!!.toInt() ?: 0);
                    }
                    count++;
                    ref.child("foods").child(count.toString()).setValue(food);

                }else{
                    ref.child("foods").child("1").setValue(food);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun updateFood(name: String, price: Float, id: Int){
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("foods")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    ref.child("name").setValue(name);
                    ref.child("price").setValue(price);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun deleteFood(id: Int){
        databaseRef.child("locals/" + auth.uid.toString()).child("foods")
            .child(id.toString()).removeValue();
    }
}