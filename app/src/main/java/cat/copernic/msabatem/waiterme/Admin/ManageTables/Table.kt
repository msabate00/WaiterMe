package cat.copernic.msabatem.waiterme.Admin.ManageTables

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Table(val name: String? = null, val max_people: Int? = null, val available: Boolean = true, var id: Int? = 0);