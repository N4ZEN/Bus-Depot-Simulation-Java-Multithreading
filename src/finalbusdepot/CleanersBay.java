
package finalbusdepot;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



//cleanign area has three slots/semaphores
public class CleanersBay implements Runnable{
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    CleanersBay (BusDepot depot){
        this.depot = depot;
    }
    String name;	
    Random random = new Random();
    Semaphore sem = new Semaphore(3);
    int rain;
    public void run() {
        docleaning(name);
        
    }
    
    //clean if not raining
    public void docleaning(String name){
       rain =random.nextInt(100);
        if(rain>=20){
            try {
                cleaning(name);
            } catch (InterruptedException ex) {
                Logger.getLogger(CleanersBay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (rain<20){
            
            System.out.println(depot.getFormatedCurrentTime()+ name + " Cleaning Bay closed due to rain. Please come back another time.");
            System.out.println(depot.getFormatedCurrentTime()+ name + " is exiting cleaning bay");
            depot.setbusesServed(depot.getbusesServed()-1);
            depot.getdepotwa().enterwaitingareafroexit(name);
            //get lock for depotwaitingarea
        } 
    }
    
    //clean
    public void cleaning(String name) throws InterruptedException{
        this.name = name;
        if (sem.availablePermits() >=1){
            sem.acquire();
            depot.setserviceStartTime(LocalTime.now());
            System.out.println(depot.getFormatedCurrentTime()+ name + " is in cleaning bay");
            Thread.sleep(100);
            sem.release();
            depot.setserviceEndTime(LocalTime.now());
            System.out.println(depot.getFormatedCurrentTime()+ name + " is exiting cleaning bay");
            depot.getdepotwa().enterwaitingareafroexit(name);
        }
        
    }
    
}
