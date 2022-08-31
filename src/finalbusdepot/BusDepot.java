
package finalbusdepot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class BusDepot implements Runnable{
    
    Day day = new Day(this);
    public Day getDay() {
	return day;
    }
    BusGen busgen = new BusGen(this);
    public BusGen getBusGen() {
	return busgen;
    }
    WaitingArea WaitingArea = new WaitingArea(this);
    public WaitingArea getWaitingArea() {
	return WaitingArea;
    }
    Ramp Ramp = new Ramp(this);
    public Ramp getRamp() {
	return Ramp;
    }
    DepotWaitingArea dwa = new DepotWaitingArea(this);
    public DepotWaitingArea getdepotwa() {
	return dwa;
    }
    CleanersBay cb = new CleanersBay(this);
    public CleanersBay getCleaningBay() {
	return cb;
    }
    MachanicsBay mb = new MachanicsBay(this);
    public MachanicsBay getMachanicsBay() {
	return mb;
    }
    private LocalDateTime time = LocalDateTime.now();
    public String getFormatedCurrentTime()
    {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
	return dtf.format(time);  
    }
    
    private LocalTime arrivalTime = LocalTime.now();
    public void setarrrivalTime(LocalTime arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public LocalTime getarrivalTime(){
        return arrivalTime;
    }
    
    private LocalTime exitTime = LocalTime.now();
    public void setexitTime(LocalTime exitTime){
        this.exitTime = exitTime;
    }
    public LocalTime getexitTime(){
        return exitTime;
    }
    
    private LocalTime serviceStart = LocalTime.now();
    public void setserviceStartTime(LocalTime serviceStart){
        this.serviceStart = serviceStart;
    }
    public LocalTime getserviceStartTime(){
        return serviceStart;
    }
    
    private LocalTime serviceEnd = LocalTime.now();
    public void setserviceEndTime(LocalTime serviceEnd){
        this.serviceEnd = serviceEnd;
    }
    public LocalTime getserviceEndTime(){
        return serviceEnd;
    }
    private LocalTime WaitingTime = LocalTime.now();
    public void setWaitingTime(LocalTime WaitingTime){
        
        this.WaitingTime = WaitingTime;
    }
    public LocalTime getWaitingTime(){
        return WaitingTime;
    }
    
    
    private LocalTime service;
    public LocalTime serviceTime(){
        return service;
    }
    LocalTime ramp;
    
    LocalTime total;
    public void settotalTime(LocalTime TotalTime){
        
        this.total = TotalTime;
    }
    public LocalTime totalTime(){
        return total;
    }
    private static volatile boolean cleaning;
    public void setCleaning(boolean cleaning)
    {
        BusDepot.cleaning = cleaning;
    }
    public boolean getcleaning()
    {
        return BusDepot.cleaning;
    }
   
    private Queue<Buses> buses = new ConcurrentLinkedQueue<>();
    public Queue<Buses> getBuses() {
	return buses;
    }
    
    int totalbuses;
    public void settotalbus(int totalbus){
        this.totalbuses = totalbus;
    }
    public int gettotalbus(){
        return totalbuses;
    }
    
    int busesserved;
    public void setbusesServed(int busesserved){
        this.busesserved = busesserved;
    }
    public int getbusesServed(){
        return busesserved;
    }
    
    int avg;
    public void setavg(int avg){
        this.avg = avg;
    }
    public int getavg(){
        return avg;
    }
     int sum ;
    public void setsum(int sum){
        this.sum = sum;
    }
    public int getsum(){
        return sum;
    }
    int bays ;
    public void setbays(int bays){
        this.bays = bays;
    }
    public int getbays(){
        return bays;
    }
    
    //calculate how many buses served
    public void addbusserved(){
        setbusesServed(getbusesServed()+ 1);
    }
    //checks how many vehicles in bays at the end of day
    public void checkBays(){
        
        int depotwasize  = getdepotwa().getdepotwasize();
        int cleaningbay = (3- getCleaningBay().sem.availablePermits());
        int MachanicsBay = (3- getMachanicsBay().sem2.availablePermits());
        setbays(depotwasize+cleaningbay+ MachanicsBay);
        
    }
    //calculates totla waiting time
    public void WaitTIme(){
        service = serviceEnd.minusNanos(serviceStart.getNano());
        service = service.plusNanos(300);
        WaitingTime = exitTime.minusNanos(arrivalTime.getNano());
        settotalTime(WaitingTime.minusNanos(service.getNano()));
        setsum(getsum() + totalTime().getNano());
        
    }
    //calculates waiting time
    public void calavg(){
        setsum(Math.abs(getsum()));
        //setavg (getsum() / gettotalbus());
        System.out.println("Total Waiting Time was " +getsum()/1000 +" seconds");
    }
    
    //if run method not here, causes error :/
    public void run(){
        
        if (getDay().isOpen()==false){
            System.out.println();
        }
    }
}
