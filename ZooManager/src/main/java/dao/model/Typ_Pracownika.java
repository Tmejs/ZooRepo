/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

/**
 *
 * @author TKK
 */
public enum Typ_Pracownika {
    OPIEKUN (1), TECHNICZNY (2), MAGAZYNIER (3);
    private final int id;

    private Typ_Pracownika(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public static Typ_Pracownika getEnumById (int id){
       for (Typ_Pracownika typ : Typ_Pracownika.values()){
           if (typ.getId() == id)
               return typ;
       } 
       return null;
    }
   
    
}
