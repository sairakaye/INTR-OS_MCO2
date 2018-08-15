package components_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train2 implements Runnable {
    private int trainID;
    private int stationID;
    private int capacity;
    private ArrayList<Robot2> robots;
    private Station2[] stations;
//    private CalTrain2 calTrain2;
    private Semaphore semLoadRobot;
    private Thread t; // Change to AnimationTimer to integrate in JavaFX.

    public Train2(int trainID, Station2[] stations, int capacity/*, CalTrain2 calTrain2*/){
        this.trainID = trainID;
        this.stationID = 0;
        this.capacity = capacity;
        this.stations = stations;
//        this.calTrain2 = calTrain2;
        this.robots = new ArrayList<>();
        this.semLoadRobot = new Semaphore(1);
        t = new Thread(this);
    }

    @Override
    public synchronized void run() {
        while(true){
            boolean isAllowToAccess = false;
            int nextStation = (stationID + 1) % 8;
            int prevStation = stationID;
            try {
                isAllowToAccess = stations[nextStation].getSemTrain().tryLock();

                if (isAllowToAccess) {
                    stationID = nextStation;
                    stations[stationID].setCurrTrain(null);
                    stations[stationID].setCurrTrain(this);
//                    calTrain2.StationStatus();

                    for(int i = robots.size() - 1; i >= 0; i--){
                        if(robots.get(i).getArrivalStation() == stations[stationID]){
//                            calTrain2.gui.RoboOut.append("Robot " + robots.get(i).getRobotID() + " has arrived at " + robots.get(i).getArrivalStation().getStationID() + "\n");
                            robots.remove(i);
                        }
                    }
                }
            } finally {
                if (isAllowToAccess) {
                    try {
                        t.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    stations[prevStation].getSemTrain().unlock();
                    station_load_train(capacity - robots.size());
                }
            }
        }
    }

    public void startThread() {
        t.start();
    }

    public void station_on_board(Station2 srcStation, int RobotIndex){
//        calTrain2.gui.RoboOut.append("Train " + trainID + "is at " + (robots.size()) + "capacity\n");
    }

    public int station_load_train(int passengerCount) {
        while (true){
            if(robots.size() == capacity){
                return 1;
            }

            if(stations[stationID].getRobots().size() == 0) {
                return 1;
            }
        }
    }

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Robot2> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot2> robots) {
        this.robots = robots;
    }

    public Station2[] getStations() {
        return stations;
    }

    public void setStations(Station2[] stations) {
        this.stations = stations;
    }

    /*public CalTrain2 getCalTrain2() {
        return calTrain2;
    }

    public void setCalTrain2(CalTrain2 calTrain2) {
        this.calTrain2 = calTrain2;
    }*/

    public Semaphore getSemLoadRobot() {
        return semLoadRobot;
    }

    public void setSemLoadRobot(Semaphore semLoadRobot) {
        this.semLoadRobot = semLoadRobot;
    }
}
