package components_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train implements Runnable {
    private int trainID;
    private int stationID;
    private int capacity;
    private ArrayList<RobotModel> robots;
    private Station[] stations;
    private Semaphore semLoadRobot;
    private Thread t; // Change to AnimationTimer to integrate in JavaFX.
    private TrainController trainController;

    public Train(int trainID, Station[] stations, int capacity){
        this.trainID = trainID;
        this.stationID = 0;
        this.capacity = capacity;
        this.stations = stations;
        this.robots = new ArrayList<>();
        this.semLoadRobot = new Semaphore(1);
        t = new Thread(this);
        this.trainController = new TrainController(this);
    }

    public TrainController getTrainController () {
        return trainController;
    }

    @Override
    public synchronized void run() {

        while(true){
            boolean isAllowToAccess = false;
            int nextStation = (stationID + 1) % 8;
            int prevStation = stationID;
            stations[prevStation].setCurrTrain(this);
            try {
                isAllowToAccess = stations[nextStation].getSemTrain().tryAcquire();

                if (isAllowToAccess) {
                    stationID = nextStation;
                    stations[prevStation].setCurrTrain(null);
                    stations[stationID].setCurrTrain(this);

                    trainController.changeStation(prevStation);
                    System.out.println("Train " + trainID + " is now at Station " + stationID);

                    for(int i = robots.size() - 1; i >= 0; i--) {
                        if(robots.get(i).getArrivalStation() == prevStation) {
                            stations[prevStation].unloadPassenger();
                            robots.get(i).setRiding();
                            System.out.println("Robot " + robots.get(i).getId() + " has arrived at Station " + robots.get(i).getArrivalStation());
                            robots.remove(i);
                        }
                    }

                    trainController.moveRight();
                }
            } finally {
                if (isAllowToAccess) {
                    try {
                        t.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    stations[prevStation].getSemTrain().release();
//                    station_load_train(capacity - robots.size());
                    if (station_load_train(capacity - robots.size()) == 1){
                        try{
                            t.sleep(2000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                } else{
                    try{
                        t.sleep(2000);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public int station_load_train(int passengerCount) {
        while (true){
            if(robots.size() == capacity) {
                System.out.println("[Train " + trainID + "] The train is full");
                return 1;
            }

            if(stationID > 0) {
                if (stations[stationID - 1].getRobots().size() == 0) {
                    System.out.println("[Train " + trainID + "] No passengers at station " + stationID);
                    return 1;
                }

                stations[stationID - 1].setCurrTrain(this);

                for (int i = 0; i < stations[stationID - 1].getRobots().size() && i < passengerCount; i++) {
                    stations[stationID - 1].loadPassenger(stations[stationID - 1].getRobots().get(i));
                }
            } else{
                if (stations[stationID].getRobots().size() == 0){
                    System.out.println("[Train " + trainID + "] No passengers at station " + stationID);
                    return 1;
                }

                stations[stationID - 1].setCurrTrain(this);

                for (int i = 0; i < stations[stationID].getRobots().size() && i < passengerCount; i++){
                    stations[stationID].loadPassenger(stations[stationID - 1].getRobots().get(i));
                }
            }
//            if(robots.size() < capacity) {
//                System.out.println("Boarding passengers");
//            }
        }
    }

    public void startThread() {
        t.start();
    }

    public void station_on_board(Station srcStation, int RobotIndex) {
        System.out.println("Train " + trainID + " is at station " + srcStation.getStationID() + " with " + (capacity - robots.size()) + " seats left");
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

    public ArrayList<RobotModel> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<RobotModel> robots) {
        this.robots = robots;
    }

    public Station[] getStations() {
        return stations;
    }

    public void setStations(Station[] stations) {
        this.stations = stations;
    }

    public Semaphore getSemLoadRobot() {
        return semLoadRobot;
    }

    public void setSemLoadRobot(Semaphore semLoadRobot) {
        this.semLoadRobot = semLoadRobot;
    }

    public Thread getThread() { return t; }
}
