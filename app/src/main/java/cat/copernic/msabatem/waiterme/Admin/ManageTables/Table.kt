package cat.copernic.msabatem.waiterme.Admin.ManageTables

class Table() {
    var id = 0;
    var name = "";
   constructor(id: Int, name: String) : this() {
       this.id = id;
       this.name = name;
   }


    fun get_Id(): Int {
        return this.id;
    }
    fun get_Name(): String {
        return this.name;
    }

    fun set_Id(id: Int){
        this.id = id;
    }
    fun set_Name(name: String){
        this.name = name;
    }

}