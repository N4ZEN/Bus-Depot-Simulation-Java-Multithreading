
package finalbusdepot;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WaitingArea {
    
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    WaitingArea(BusDepot depot){
        this.depot = depot;
    }
    private String Name;
    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    private int remainingspots;
    public void setremainingspots(int remainingspots){
        this.remainingspots = remainingspots;
    }
    public int getremainingspots(){
        return remainingspots;
    }
    
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(20, true);
    public BlockingQueue<String> getwaqueue(){
        return queue;
    }
    
    
    //enter waiting area
    public synchronized void addtoqueue(String name) throws InterruptedException{
        queue.put(name);
        System.out.println(depot.getFormatedCurrentTime()+ name + " is now in waiting area.");
        Thread.sleep(100);
        remainingspots = (20 - queue.size());
        
        }
        
    //exit waiting area
    public synchronized void takefromqueue(String name){
        this.Name = name;
        try {
            name = queue.take();
            setName(name);
            System.out.println(depot.getFormatedCurrentTime()+ name + " is leaving outside waiting area. ");
            setremainingspots(20 - queue.size());
        
        } catch (InterruptedException ex) {
            Logger.getLogger(WaitingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
