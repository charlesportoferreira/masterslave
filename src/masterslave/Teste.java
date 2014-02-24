/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package masterslave;

/**
 *
 * @author charles
 */
public class Teste {
    
    
    public static void main(String[] args){
        Master m = new Master();
        Thread t = new Thread(m);
        t.start();
    }
}
