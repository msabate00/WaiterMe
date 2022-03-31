package cat.copernic.msabatem.waiterme

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.waiterme.Admin.ManageFood.Food
import cat.copernic.msabatem.waiterme.Admin.ManageFood.FoodViewAdapter
import cat.copernic.msabatem.waiterme.Admin.ManageTables.Table
import cat.copernic.msabatem.waiterme.Admin.ManageTables.TablesViewAdapter
import cat.copernic.msabatem.waiterme.Recepcionist.Tables.TablesRViewAdapter
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.FoodsWViewAdapter
import cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders.OrderItem
import cat.copernic.msabatem.waiterme.Waiter.Tables.TablesWViewAdapter
import com.google.android.gms.tasks.Task
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
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.max

class Utils {
    private val database =
        FirebaseDatabase.getInstance("https://waiterme-default-rtdb.europe-west1.firebasedatabase.app/");
    private val storage = FirebaseStorage.getInstance("gs://waiterme.appspot.com");


    private val auth = Firebase.auth;
    private val databaseRef = database.reference;
    private val storageRef = storage.getReference().child("/locals/${auth.uid}/images/")

    companion object {
        const val TABLE_ADMIN = 1;
        const val TABLE_RECEPTIONIS = 2;
        const val TABLE_WAITER = 3;

        const val FOOD_ADMIN = 1;
        const val FOOD_WAITER = 3;

    }

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

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    fun getTables(f: Fragment, rv: RecyclerView, type: Int) {

        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var tables: ArrayList<Table> = arrayListOf<Table>();

        ref.child("tables").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (tableSnapshot in snapshot.children) {
                        var table = tableSnapshot.getValue<Table>();
                        table!!.id = tableSnapshot.key!!.toInt();
                        tables.add(table!!);
                        when (type) {
                            1 -> rv.adapter = TablesViewAdapter(tables);
                            2 -> rv.adapter = TablesRViewAdapter(tables);
                            3 -> rv.adapter = TablesWViewAdapter(tables);
                        }

                    }

                    /*Log.i("AYUDA","ANTES" + tables[0].name)
                    rv.adapter = TablesViewAdapter(tables);
                    Log.i("AYUDA","DESPUES")*/
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: ", "Error: " + "code " + error.code + ", " + error.details);
            }
        });

    }

    fun addTable(name: String, max_people: Int) {
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        ref.child("tables").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var table = Table(name, max_people);
                if (snapshot.exists()) {
                    var count = 0;
                    for (snapshotData in snapshot.children) {
                        count = max(count, snapshotData.key!!.toInt() ?: 0);
                    }
                    count++;
                    table.id = count;
                    ref.child("tables").child(count.toString()).setValue(table);

                } else {
                    table.id = 1;
                    ref.child("tables").child("1").setValue(table);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun updateTable(name: String, max_people: Int, id: Int) {
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    ref.child("name").setValue(name);
                    ref.child("max_people").setValue(max_people);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun deleteTable(id: Int) {
        databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString()).removeValue();
    }


    fun editAvaileableTable(id: Int, b: Boolean) {
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("tables")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    ref.child("available").setValue(b);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    fun getFood(f: Fragment, rv: RecyclerView, activity: MainActivity, type: Int) {

        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var foods: ArrayList<Food> = arrayListOf<Food>();

        ref.child("foods").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (foodSnapshot in snapshot.children) {
                        var food = foodSnapshot.getValue<Food>();
                        food!!.id = foodSnapshot.key!!.toInt();
                        foods.add(food!!);
                        when (type) {
                            FOOD_ADMIN -> rv.adapter = FoodViewAdapter(activity, foods);
                            FOOD_WAITER -> rv.adapter = FoodsWViewAdapter(foods);
                        }

                    }
                    //rv.adapter = FoodViewAdapter(activity,foods);
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: ", "Error: " + "code " + error.code + ", " + error.details);
            }
        });

    }

    fun addFood(name: String, price: Float) {
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        ref.child("foods").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var food = Food(name, price);
                if (snapshot.exists()) {
                    var count = 0;
                    for (snapshotData in snapshot.children) {
                        count = max(count, snapshotData.key!!.toInt() ?: 0);
                    }
                    count++;
                    food.id = count;
                    ref.child("foods").child(count.toString()).setValue(food);

                } else {
                    food.id = 1;
                    ref.child("foods").child("1").setValue(food);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun updateFood(name: String, price: Float, id: Int) {
        val ref = databaseRef.child("locals/" + auth.uid.toString()).child("foods")
            .child(id.toString());
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    ref.child("name").setValue(name);
                    ref.child("price").setValue(price);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun deleteFood(id: Int) {
        databaseRef.child("locals/" + auth.uid.toString()).child("foods")
            .child(id.toString()).removeValue();
    }

    fun getFoodImage(id: Int, iv: ImageView) {
        val pic = storageRef.child("/food/id_$id.jpg");
        val picBytes = pic.getBytes(5000000)

        picBytes.addOnSuccessListener {
            var bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)

            iv.setImageBitmap(bitmap)
        }.addOnFailureListener {
        }
    }

    fun addOrder(table_id: Int, foods_id: List<Int>, details: String?) {
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        ref.child("orders").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var orderItem = OrderItem(
                    table_id = table_id,
                    foods_id = foods_id,
                    details = details,
                    final_price = getAllPriceFromFood2(foods_id)
                );
                if (snapshot.exists()) {
                    var count = 0;
                    for (snapshotData in snapshot.children) {
                        count = max(count, snapshotData.key!!.toInt() ?: 0);
                    }
                    count++;
                    orderItem.id = count;
                    ref.child("orders").child(count.toString()).setValue(orderItem);

                } else {
                    orderItem.id = 1;
                    ref.child("orders").child("1").setValue(orderItem);
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun getAllPriceFromFood(foods_id: List<Int>): Float {
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var foods: ArrayList<Food> = arrayListOf<Food>();
        var price = 0f;

        val dataSnapshotTask: Task<DataSnapshot> = ref.child("foods").get();

        dataSnapshotTask.addOnCompleteListener {
            Log.i("AYUDA", "HEY TERMINO")
            if (it.result.exists()) {
                for (foodSnapshot in it.result.children) {
                    var food = foodSnapshot.getValue<Food>();
                    food!!.id = foodSnapshot.key!!.toInt();
                    if (foods_id.contains(food.id)) {
                        price += food.price!!;
                    }
                }
                //rv.adapter = FoodViewAdapter(activity,foods);
            }
        }
        return price;
    }

    fun getAllPriceFromFood2(foods_id: List<Int>): Float {

        var price = 0f;
        val ref = databaseRef.child("locals/" + auth.uid.toString());
        var foods: ArrayList<Food> = arrayListOf<Food>();

        ref.child("foods").addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (foodSnapshot in snapshot.children) {
                        var food = foodSnapshot.getValue<Food>();
                        food!!.id = foodSnapshot.key!!.toInt();
                        foods.add(food!!);
                        price += food.price!!;
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("The read failed: ", "Error: " + "code " + error.code + ", " + error.details);
            }
        }).let {
            return price;
        };


    }

}