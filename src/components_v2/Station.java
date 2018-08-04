package components_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Station {
    private int stationID;
    private Train currTrain;
    private ArrayList<Robot> robots = new ArrayList<>();
    private Semaphore semTrain;
    private Semaphore semRobot;
    private boolean lock = false;

    public Station(int stationID){
        this.stationID = stationID;
        semTrain = new Semaphore(1);
        semRobot = new Semaphore(1);
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public Train getCurrTrain() {
        return currTrain;
    }

    public void setCurrTrain(Train currTrain) {
        this.currTrain = currTrain;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public Semaphore getSemTrain() {
        return semTrain;
    }

    public void setSemTrain(Semaphore semTrain) {
        this.semTrain = semTrain;
    }

    public Semaphore getSemRobot() {
        return semRobot;
    }

    public void setSemRobot(Semaphore semRobot) {
        this.semRobot = semRobot;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
