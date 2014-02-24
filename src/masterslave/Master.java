/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterslave;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class Master implements Runnable {

    int numMesagens = 12;
    int maxSlaves = 4;
    //Slave[] escravos;
    Counter contador;
    Lock trava;

    public Master() {
        //escravos = new Slave[numMesagens];
        this.contador = new Counter();
        this.trava = new ReentrantLock();
    }

    @Override
    public void run() {

        for (int i = 0; i < numMesagens; i++) {
            System.out.println("Escravo: " + i);
              trava.lock();
            try {
                contador.inc();
            } finally {
                trava.unlock();
            }
            Slave escravo = new Slave(i, "teste", contador, trava, this);
            Thread t = new Thread(escravo);
            t.start();
            if (contador.getCount() == maxSlaves) {
                try {
                    synchronized (this) {
                        this.wait();
                        System.out.println("eu master acordei");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            }

        }
    }

}
