package cat.copernic.msabatem.waiterme

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Utils {
    private var database = FirebaseDatabase.getInstance("https://waiterme-default-rtdb.europe-west1.firebasedatabase.app/");
    private var auth = Firebase.auth;


    fun getDatabase(): FirebaseDatabase {
        return database;
    }
    fun getAuth(): FirebaseAuth {
        return auth;
    }

}