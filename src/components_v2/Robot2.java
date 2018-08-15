package components_v2;

public class Robot2 implements Runnable {
    private Station2 departureStation;
    private Station2 arrivalStation;
    private boolean isOnTrain;
    private int robotID;
    private Thread t; // Change to AnimationTimer when integrating JavaFX.

    public Robot2(Station2 departureStation, Station2 desStation, int RobotID){
        this.departureStation = departureStation;
        this.arrivalStation = desStation;
        this.robotID = RobotID;
        isOnTrain = false;
        t = new Thread(this);

        robotCreation();
    }

    @Override
    public void run() {
        station_wait_for_train();
    }

    public void startThread() {
        t.start();
    }

    public synchronized int station_wait_for_train() {
        boolean waiting = true;
        while (waiting) {
            if (isOnTrain && departureStation.getCurrTrain() != null){
                return 1;
            } else {
                boolean isAllowRobot = false;
                boolean isAllowLoading = false;
                try {
                    isAllowRobot = departureStation.getSemRobot().tryLock();
                    if(departureStation.getCurrTrain() != null){
                        isAllowLoading = departureStation.getCurrTrain().getSemLoadRobot().tryAcquire();
                        System.out.println(isAllowRobot + " - " + isAllowLoading);
                        if (isAllowRobot && isAllowLoading && departureStation.getCurrTrain() != null && departureStation.getCurrTrain().getCapacity() - departureStation.getCurrTrain().getRobots().size() > 0) {
//                            gui.RoboOut.append("Robot " + robotID + " rides the train going to " + arrivalStation.getStationID() + "\n");
                            departureStation.getCurrTrain().getRobots().add(this);
                            departureStation.getRobots().remove(this);
                            departureStation.getCurrTrain().station_on_board(departureStation, departureStation.getRobots().indexOf(this));
                            waiting = false;
                        }
                    }
                } finally {
                    if (isAllowLoading) {
                        departureStation.getCurrTrain().getSemLoadRobot().release();
                    }
                    if (isAllowRobot) {
                        departureStation.getSemRobot().unlock();
                    }
                }
            }
        }
        return 0;
    }

    public void robotCreation() {
        boolean isAllowToAccess = false;
        try {
            isAllowToAccess = departureStation.getSemRobot().tryLock();
//            isAllowToAccess = departureStation.getSemRobot().tryAcquire();
            if (isAllowToAccess) {
                this.departureStation.getRobots().add(this);
                System.out.println(departureStation.getStationID() + "-" + departureStation.getRobots().size());
            }
        } finally {
            if (isAllowToAccess) {
                this.departureStation.getSemRobot().unlock();
            }
        }
    }

    public Station2 getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station2 departureStation) {
        this.departureStation = departureStation;
    }

    public Station2 getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station2 arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public boolean isOnTrain() {
        return isOnTrain;
    }

    public void setOnTrain(boolean onTrain) {
        isOnTrain = onTrain;
    }

    public int getRobotID() {
        return robotID;
    }

    public void setRobotID(int robotID) {
        this.robotID = robotID;
    }
}
