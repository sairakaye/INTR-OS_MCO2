package components;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
    private int id;
    private ArrayList<Robot> waitingList;
    private ArrayList<Thread> robotsThreads;
    private boolean trainWaiting; // train waiting in station
    private Train trainOnStation;
    private Lock lockStation;
    private Condition condStation;

    public Station(int id){
        this.id = id;
        trainWaiting = false;
        waitingList = new ArrayList<>();
        robotsThreads = new ArrayList<>();
    }

    public void station_load_train(int count) {

    }

    public void station_wait_for_train() {
        // check for mutex?

        // while station trainWaiting is false, do nothing.
        // while (!trainWaiting);

        int availableSlots = trainOnStation.getCapacity() - trainOnStation.getNumberOfPassengers();

        // if mas konti yung naghihintay kesa sa availableSlots,
        if (waitingList.size() <= availableSlots)
            station_load_train(waitingList.size());
        else
            station_load_train(availableSlots);
    }

    public void station_on_board(Robot passenger){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // reduce passengers in waiting list (train arrived)
    public void reduceWaiting(){

    }

    public void addWaitingRobots(int numRobots, int stationDestination) {

    }

    public Train getTrainOnStation(){
        return trainOnStation;
    }

    public void setTrainOnStation(Train trainOnStation){
        this.trainOnStation = trainOnStation;
    }

    public boolean getTrainWaiting(){
        return trainWaiting;
    }

    public void lock_init(){
        lockStation = new ReentrantLock();
    }

    public void lock_acquire(){

    }

    public void lock_release(){

    }

    public void cond_init(){

    }

    public void cond_wait(){

    }

    public void cond_signal(){

    }

    public void cond_broadcast(){

    }
}
