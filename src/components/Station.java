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

    public void station_load_train(Robot pass) {

    }

    public void station_wait_for_train(Robot pass) {
        // check for mutex?
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        station_load_train(pass);
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
