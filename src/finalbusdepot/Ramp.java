
package finalbusdepot;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Ramp{
    private BusDepot depot;
    BusDepot getDepot() {
        return depot;
    }
    Ramp(BusDepot depot){
        this.depot = depot;
    }
    String name;

    private Lock ramplock= new ReentrantLock(true);
    
    private Lock depotwaitingarealock = new ReentrantLock(true);

    //acquire lock for ramp --two locks needed
    private void aquirelocks(Lock firstlock, Lock secondlock)throws InterruptedException{
        while(true){
            boolean gotfirstlock = false;
            boolean gotsecondlock = false;
            try{
                gotfirstlock = firstlock.tryLock();
                gotsecondlock = secondlock.tryLock();
                
            }
            finally{
                if (gotfirstlock && gotsecondlock){
                    
                    return;
                }
                else if(gotfirstlock){
                    firstlock.unlock();
                }
                else if(gotsecondlock){
                    secondlock.unlock();
                }
            }
            
        }
        
    }
    //enter through ramp
    public synchronized void enterPassRamp(String name) throws InterruptedException{
        
            aquirelocks(ramplock,depotwaitingarealock);
            depot.getWaitingArea().takefromqueue(name);
            System.out.println(depot.getFormatedCurrentTime()+ name + " is going in through the ramp");
            try{
                 Thread.sleep(300);
             }
            finally{
            
            ramplock.unlock();
            
            depotwaitingarealock.unlock();
            
            System.out.println(depot.getFormatedCurrentTime()+ name + " has passed the ramp");
            
            }

    }
    //exit through ramp
    public synchronized void exitPassRamp(String name) throws InterruptedException{
        
            ramplock.tryLock();
            System.out.println(depot.getFormatedCurrentTime()+ name + " is going out through the ramp. ");
        try{
            Thread.sleep(300);
        }
                finally{
            
            ramplock.unlock();
            System.out.println(depot.getFormatedCurrentTime()+ name + " has exited the bus depot. Good Bye!");
            
        }
        
    }

       
}
