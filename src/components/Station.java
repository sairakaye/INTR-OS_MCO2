package components;
import java.util.ArrayList;

//8 Stations total
public class Station {
    private int id;
    private ArrayList<Robot> waitingList;
    private ArrayList<Thread> robotsThreads;

    public void station_load_train(int count) {

    }

    public void station_wait_for_train() {

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
}
