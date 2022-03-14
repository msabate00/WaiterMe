package cat.copernic.msabatem.waiterme.Recepcionist.Tables

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TableR(val name: String? = null, val max_people: Int? = null, val available: Boolean = true, var id: Int? = 0);