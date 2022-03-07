package cat.copernic.msabatem.waiterme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDate
import kotlin.math.max

class Utils {
    private val database = FirebaseDatabase.getInstance("https://waiterme-default-rtdb.europe-west1.firebasedatabase.app/");
    private val auth = Firebase.auth;
    private val databaseRef = database.reference;


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



    fun getTables(): ArrayList<Table> {

        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var tables: ArrayList<Table> = arrayListOf<Table>();

        ref.child("tables").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                if(snapshot.exists()){
                    var contador = 0;
                    //var table = Table("1",2,false);

                    for (tableSnapshot in snapshot.children) {

                        Log.i("AYUDA", tableSnapshot.value.toString());

                    }
                    for (tableSnapshot in snapshot.children) {
                        var table = tableSnapshot.getValue<Table>();
                        tables.add(table!!);
                    }

                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: " , "Error: "+ "code " + error.code + ", " + error.details );
            }
        });

        return tables;
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


                    Log.i("AYUDA", count.toString());
                    //ref.child("tables")
                    ref.child("tables").child(count.toString()).setValue(table);

                }else{
                    ref.child("tables").child("1").setValue(table);
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }





}