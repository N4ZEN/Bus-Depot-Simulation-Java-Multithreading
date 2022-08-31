
package finalbusdepot;

import static java.lang.Thread.currentThread;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BusGen{
    
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    BusGen(BusDepot depot){
        this.depot = depot;
    }
   
 
    
    
    
    private volatile boolean flag;
    public void setflag(boolean flag)
    {
        this.flag = flag;
    }
    public boolean getflag()
    {
        return this.flag;
    }

    
    private final AtomicInteger busID = new AtomicInteger(0);

    //generates buses while bus depot is open
    public void busGen(){
        
        while(depot.getDay().genNewBuses()==true){
            int id = busID.getAndIncrement();  
            depot.settotalbus(id);
            //depot.setbusesServed(0);
            Buses b = new Buses(depot, "  Bus " + Integer.toString(id) +": ");

            Thread bus = new Thread(b);
            b.setID(id);
            bus.setName(b.getName());
            bus.start();
            currentThread().setName(b.getName());
            depot.getarrivalTime();
            
            System.out.println(depot.getFormatedCurrentTime()+ b.getName() +" has arrived at the bus Depot");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BusGen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try
                {
                    TimeUnit.SECONDS.sleep((long)(Math.random()*2));
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
           

        }

        
        if(depot.getDay().genNewBuses()==false){
            
            System.out.println(depot.getFormatedCurrentTime()+ "Bus depot is closing. Please comeback tommorow. ");
            stopRunning();
        
        }
    }
    

    public void stopRunning()
    {
        flag = false;
        
        
    }    
    
        
    
      
}

