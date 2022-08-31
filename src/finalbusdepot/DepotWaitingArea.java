
package finalbusdepot;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;




public class DepotWaitingArea {
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    DepotWaitingArea (BusDepot depot){
        this.depot = depot;
    }
    
    String name; 
    Random random = new Random();
    int chooseservice;
    private int wasize;
    public void setwasize(int size){
        this.wasize = size;
    }
    public int getwasize(){
        return wasize;
    }
    
    
    private ConcurrentLinkedQueue<String> cleaning = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<String> getcleaningqueue(){
        return cleaning;
    }
    private ConcurrentLinkedQueue<String> machanic = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<String> getmachanicqueue(){
        return machanic;
    }
    private ConcurrentLinkedQueue<String> exit = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<String> getexitqueue(){
        return exit;
    }
    private Queue<Buses> buses = new ConcurrentLinkedQueue<>();
    public Queue<Buses> getBuses() {
	return buses;
    }
    
    
    public int getdepotwasize(){
        wasize = getcleaningqueue().size() + getexitqueue().size()+ getmachanicqueue().size();
        setwasize(wasize);
        return wasize;
    }
    
    //enter waiting area- witing for service
    public synchronized void enterwaitingareaforservice(String name){
        chooseservice = random.nextInt(100);
        this.name = name;

            if(chooseservice <50){
                depot.setCleaning(true);
            getcleaningqueue().add(name);
            System.out.println(depot.getFormatedCurrentTime()+ name + " is waiting for cleaning bay");
            System.out.println(depot.getFormatedCurrentTime()+ name + " is now in depot waiting area");
            }
            else {
                depot.setCleaning(false);
                getmachanicqueue().add(name);
                System.out.println(depot.getFormatedCurrentTime()+ name + " is waiting for machanics bay");
                System.out.println(depot.getFormatedCurrentTime()+ name + " is now in depot waiting area");
            }
    }
    
    //enter waiting area-- waiting for ramp to be free
    public synchronized void enterwaitingareafroexit(String name){
        getexitqueue().add(name);
        System.out.println(depot.getFormatedCurrentTime()+ name + " is waiting to exit");
    }
    
}
