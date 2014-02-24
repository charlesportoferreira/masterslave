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
public class Slave implements Runnable {

    private final Master master;

    private int id;
    private String message;
    private final Counter contador;
    private final Lock trava;

    public Slave(int id, String message, Counter contador, Lock trava, Master master) {
        this.master = master;
        this.id = id;
        this.message = message;
        this.contador = contador;
        this.trava = trava;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        try {
            synchronized (this) {
                this.wait(2000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Slave.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Acordei: " + this.getId());
        //trava.lock();

        //System.out.println("peguei a trava, sou " + this.getId() + " " + contador.getCount());
        trava.lock();
        try {
            contador.dec();
        } finally {
            trava.unlock();
        }
        System.out.println("Eu sou a Thread: " + this.getId() + " e o contador agora vale: " + contador.getCount());
        synchronized (master) {
            //System.out.println("Vou acordar a master: " + this.getId());
            master.notify();

        }
    }

}
