
package finalbusdepot;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;





public class Buses implements Runnable {
    
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }

    
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public Buses(BusDepot depot, String name){
        this.depot = depot;
        this.name = name;
    }

    
    private int id;
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    
    private int serviceTime; 
    public void setserviceTime(int time){
        this.serviceTime = time;
    }
    public int getserviceTime(){
        return serviceTime;
    }
    
    public void run(){
        
         synchronized (this){  
             depot.getBuses().add(this);
                       
             try {
                 depot.getWaitingArea().addtoqueue(Thread.currentThread().getName());
                 
             } catch (InterruptedException ex) {
                 java.util.logging.Logger.getLogger(Buses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             }
             
             if(depot.getdepotwa().getwasize()<20){    
                try{
                    depot.getRamp().enterPassRamp(Thread.currentThread().getName());
                    depot.getdepotwa().enterwaitingareaforservice(Thread.currentThread().getName());
                    Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Buses.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             else{
                 depot.getWaitingArea().takefromqueue(Thread.currentThread().getName());
             }

             //if buses are dirty send to cleaning bay
             if(depot.getcleaning()==false){
             
                 if (depot.getMachanicsBay().checkdirty()==true){
                     System.out.println(depot.getFormatedCurrentTime()+ Thread.currentThread().getName() + " is dirty");
                     depot.setCleaning(true);
                     depot.getdepotwa().getmachanicqueue().poll();
                     depot.getCleaningBay().docleaning(Thread.currentThread().getName());
                     depot.getdepotwa().getcleaningqueue().poll();
                     System.out.println(depot.getFormatedCurrentTime()+ Thread.currentThread().getName() + " is waiting for cleaning bay");
                     System.out.println(depot.getFormatedCurrentTime()+ Thread.currentThread().getName() + " is now in depot waiting area");
                 }
            
                 else if (depot.getMachanicsBay().checkdirty()==false){
                     depot.getMachanicsBay().doservice(Thread.currentThread().getName());
                    depot.getdepotwa().getmachanicqueue().poll();
                 }
             }
             else if (depot.getcleaning()==true){
                 depot.getCleaningBay().docleaning(Thread.currentThread().getName());
                 depot.getdepotwa().getcleaningqueue().poll();
             }
        
             
             
             try {
                 depot.getRamp().exitPassRamp(name);
                 depot.getdepotwa().getexitqueue().poll();
                 depot.addbusserved();
                 depot.setexitTime(LocalTime.now());
             } catch (InterruptedException ex) {
                 Logger.getLogger(Buses.class.getName()).log(Level.SEVERE, null, ex);
             }
             
}
    }
}



