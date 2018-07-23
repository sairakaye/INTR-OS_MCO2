package components;
import java.util.ArrayList;

public class Station {
    private int id;
    private ArrayList<Robot> waitingList;
    private ArrayList<Thread> robotsThreads;
    private boolean trainWaiting; // train waiting in station
    private Train trainOnStation;

    public Station(int id){
        this.id = id;
        trainWaiting = false;
        waitingList = new ArrayList<>();
        robotsThreads = new ArrayList<>();

    }

    public void station_load_train(int count) {

    }

    public void station_wait_for_train() {

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
}
