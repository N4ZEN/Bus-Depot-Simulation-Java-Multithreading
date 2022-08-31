
package finalbusdepot;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Day implements Runnable{
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    Day (BusDepot depot){
        this.depot= depot;
    }
    
    private static volatile boolean open;
    public void setOpen(boolean open)
    {
        Day.open = open;
    }
    public boolean isOpen()
    {
        return Day.open;
    }
    
    private static volatile boolean newbuses;
    public void setNewBuses(boolean newbuses)
    {
        Day.newbuses = newbuses;
    }
    public boolean genNewBuses()
    {
        return Day.newbuses;
    }

    //opens the bus depot and generates new buses and warns about 30 mins till closing time( no new buses can enter 30 mins to closing time) and closes depot
    public void run(){
        
        setOpen(true);
        setNewBuses(true);
        System.out.println(depot.getFormatedCurrentTime()+ "Bus Depot is open! ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setNewBuses(false);
        System.out.println(depot.getFormatedCurrentTime()+ "Bus Depot is closing in 30 mins. ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setOpen(false);
        depot.checkBays();
        System.out.println(depot.getFormatedCurrentTime()+ "Bus Depot has closed");
    }
}