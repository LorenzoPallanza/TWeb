/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.demo;

/**
 *
 * @author liliana
 */
public class Operazione {
    
    /* metodo che restituisce il valore dell'operazione richiesta dall'utente in formato esterno */
    public String getOperazione(String info) {
     String out = "";
     if (info.equals("nessuna"))
          out = "nessuna informazione";
     else if (info.equals("persone"))
          out = "persone";
          else if (info.equals("conti"))
                     out = "conti correnti bancari";
                else out = "tipo di informazione sconosciuto";
                return out; 
    }
}
