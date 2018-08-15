package components_v2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station2 {
    private int stationID;
    private Train2 currTrain;
    private ArrayList<Robot2> robots = new ArrayList<>();
    private Lock lockTrain;
    private Lock lockRobot;
    private boolean lock = false;

    public Station2(int stationID){
        this.stationID = stationID;
        lockTrain = new ReentrantLock();
        lockRobot = new ReentrantLock();
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public Train2 getCurrTrain() {
        return currTrain;
    }

    public void setCurrTrain(Train2 currTrain) {
        this.currTrain = currTrain;
    }

    public ArrayList<Robot2> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot2> robots) {
        this.robots = robots;
    }

    public Lock getSemTrain() {
        return lockTrain;
    }

    public void setSemTrain(Lock lockTrain) {
        this.lockTrain = lockTrain;
    }

    public Lock getSemRobot() {
        return lockRobot;
    }

    public void setSemRobot(Lock semRobot) {
        this.lockRobot = semRobot;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
