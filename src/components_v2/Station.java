package components_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Station {
    private int stationID;
    private Train currTrain;
    private ArrayList<RobotModel> robots = new ArrayList<>();
    private Semaphore semTrain;
    private Semaphore semRobot;
    private boolean lock = false;
    private CalTrainController controller;

    public Station(int stationID, CalTrainController controller){
        this.stationID = stationID;
        semTrain = new Semaphore(1);
        semRobot = new Semaphore(1);
        this.controller = controller;
    }

    public void loadPassenger(RobotModel robot){
        boolean allowed = currTrain.getSemLoadRobot().tryAcquire();

        if(allowed){
            robot.setRiding();
            currTrain.getRobots().add(robot);
            robots.remove(robot);
            currTrain.getSemLoadRobot().release();
            System.out.println("Passenger " + robot.getId() + " has boarded at Station " + robot.getArrivalStation());
            controller.boardPassenger(stationID);
        }
    }

    public void unloadPassenger(){
        controller.disembarkMiddleman(stationID);
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

    public ArrayList<RobotModel> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<RobotModel> robots) {
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
