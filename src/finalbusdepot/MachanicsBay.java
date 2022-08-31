
package finalbusdepot;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachanicsBay implements Runnable {
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    MachanicsBay (BusDepot depot){
        this.depot = depot;
    }
    Random random = new Random();
    Semaphore sem2 = new Semaphore(3);
    String name;
    int serviceNeeded;
    int isdirty;
    
    //three semaphones/bays in machanics
    public void doservice(String name){
        this.name = name;
        if (sem2.availablePermits() >=1){
            try {
                try {
                    sem2.acquire();
                    depot.setserviceStartTime(LocalTime.now());
                    System.out.println(depot.getFormatedCurrentTime()+ name + " is in machanics bay");
                } catch (InterruptedException ex) {
                    Logger.getLogger(MachanicsBay.class.getName()).log(Level.SEVERE, null, ex);
                }
                service();
                sem2.release();
                depot.setserviceEndTime(LocalTime.now());
                System.out.println(depot.getFormatedCurrentTime()+ name + " is exiting machanics bay");
                depot.getdepotwa().enterwaitingareafroexit(name);
            } catch (InterruptedException ex) {
                Logger.getLogger(MachanicsBay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void run() {
        
            doservice(name);
        
        
    }
    private boolean dirty;
    public void setdirty(boolean dirty){
        this.dirty  =dirty;
    }
    public boolean isDirty(){
        return dirty;
    }
    //checks if dirty, sends to cleaning bay
    public boolean checkdirty(){
        isdirty =random.nextInt(100);
        if(isdirty>=50){
            setdirty(true);
            
        }
        
        else if(isdirty<50){
            setdirty(false);
            
        }
        return dirty;
    }
    //services
    public void service() throws InterruptedException{
        serviceNeeded =random.nextInt(100);
        
        if(serviceNeeded>=40){
            oilchange();
            
        }
        else if (serviceNeeded>=20 && serviceNeeded <40){
            oilchange();
            refuel();
        }
        else if(serviceNeeded<20){
            oilchange();
            repairs();
        }
        
    }
    public void oilchange() throws InterruptedException{
        Thread.sleep(10);
        System.out.println(depot.getFormatedCurrentTime()+ name + " needs oil change");
    }
    public void refuel() throws InterruptedException{
        Thread.sleep(20);
        System.out.println(depot.getFormatedCurrentTime()+ name +" needs to be refueled");
    }
    public void repairs() throws InterruptedException{
        Thread.sleep(30);
        System.out.println(depot.getFormatedCurrentTime()+ name + " needs to be repaired");
    }
    
    
    
        
    }

