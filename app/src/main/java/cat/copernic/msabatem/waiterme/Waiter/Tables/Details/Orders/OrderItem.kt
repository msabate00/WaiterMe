package cat.copernic.msabatem.waiterme.Waiter.Tables.Details.Orders

import cat.copernic.msabatem.waiterme.MainActivity
import cat.copernic.msabatem.waiterme.R

data class OrderItem(var id: Int? = 0, val table_id: Int? = 0, val foods_id: List<Int>? = List<Int>(1) { 0 }, val final_price: Float? = 0f,
                     val details: String? = "", val finished: Boolean? = false);
