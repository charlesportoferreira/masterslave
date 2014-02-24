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
public class Counter {

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void inc() {

        count++;

    }

    public void dec() {

        count--;

    }

}
