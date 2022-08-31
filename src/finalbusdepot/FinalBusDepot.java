package finalbusdepot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naza Zuhair
 */
public class FinalBusDepot implements Runnable{
         private volatile boolean fflag;
         public void setfflag(boolean fflag)
         {
            this.fflag = fflag;
         }
         public boolean getfflag()
         {
             return this.fflag;
         }
         public void run(){
             
         }
         
    
    public static void main(String[] args) {

        
        BusDepot dp = new BusDepot();
        Thread dept = new Thread(dp);
        dept.start();
        Thread day = new Thread(new Day(dp));
        day.start();
             
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
                 Logger.getLogger(FinalBusDepot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        while(dp.getDay().isOpen()==true){
            BusGen bg = new BusGen(dp);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BusGen.class.getName()).log(Level.SEVERE, null, ex);
            }
            bg.setflag(true);
            while (bg.getflag()==true){
                bg.busGen();
            }
        
        }
        if (dp.getDay().isOpen()== false){
            System.out.println("ending day");
            
            
        }
          
        
        dp.WaitTIme();
        dp.calavg();
        dp.gettotalbus();
        System.out.println("Total buses Arrived is " +dp.gettotalbus());
        dp.getbusesServed();
        System.out.println("Total buses served is " +dp.getbusesServed());
        dp.getdepotwa().getdepotwasize();
        System.out.println("Buses still in Bays = "+ dp.getdepotwa().getdepotwasize());
//        System.out.println(dp.getdepotwa().getexitqueue().size());
//        System.out.println(dp.getdepotwa().getmachanicqueue().size());
//        System.out.println(dp.getdepotwa().getcleaningqueue().size());
//        System.out.println(dp.getdepotwa().getdepotwasize());        
//        
    }
    public void stoprunning()
        {
            fflag = false;
        } 
    
}
