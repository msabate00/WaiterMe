package cat.copernic.msabatem.waiterme.Admin.ManageFood

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Food(val name: String? = null, val price: Float? = null, val image: String? = null,val available: Boolean = true, var id: Int? = 0);