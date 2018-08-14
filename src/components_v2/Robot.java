package components_v2;

public class Robot implements Runnable {
    private Station departureStation;
    private Station arrivalStation;
    private boolean isOnTrain;
    private int robotID;
    private Thread t; // Change to AnimationTimer when integrating JavaFX.

    public Robot(Station departureStation, Station desStation, int RobotID){
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

    public int station_wait_for_train() {
        boolean waiting = true;
        while (waiting) {
            if (isOnTrain && departureStation.getCurrTrain() != null) {
                return 1;
            } else {
                boolean isAllowRobot = false;
                boolean isAllowLoading = false;
                try {
                    isAllowRobot = departureStation.getSemRobot().tryAcquire();
                    if(departureStation.getCurrTrain() != null){
                        isAllowLoading = departureStation.getCurrTrain().getSemLoadRobot().tryAcquire();
                        System.out.println(isAllowRobot + " - " + isAllowLoading);
                        if (isAllowRobot && isAllowLoading && departureStation.getCurrTrain() != null && departureStation.getCurrTrain().getCapacity() - departureStation.getCurrTrain().getRobots().size() > 0) {
                            System.out.println("Robot " + robotID + " rides the train on station " + departureStation.getStationID() + " going to " + arrivalStation.getStationID());
//                            departureStation.getCurrTrain().getRobots().add(this);
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
                        departureStation.getSemRobot().release();
                    }
                }
            }
        }
        return 0;
    }

    public void robotCreation() {
        boolean isAllowToAccess = false;
        try {
            isAllowToAccess = departureStation.getSemRobot().tryAcquire();
            if (isAllowToAccess) {
//                this.departureStation.getRobots().add(this);
                System.out.println(departureStation.getStationID() + "-" + departureStation.getRobots().size());
            }
        } finally {
            if (isAllowToAccess) {
                this.departureStation.getSemRobot().release();
            }
        }
        System.out.println("Robot " + robotID + " is now at Station " + departureStation.getStationID());
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
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
