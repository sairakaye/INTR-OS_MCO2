package components;

public class Robot implements Runnable {
    private static int id = 0;
    private int arrivalStation; //which station passenger rode
    private int departureStation; //which station passenger is going to
    private volatile boolean isOnTrain;
    private Station currentStation;
    private Thread t;

    public Robot(Station currentStation, int id, int arrivalStation, int departureStation) {
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.currentStation = currentStation;
        this.id = id;
        isOnTrain = false;
        t = new Thread(this);

        id++;

    }

    // For testing purposes.
    public Robot() {
        t = new Thread(this);
    }

    @Override
    public synchronized void run() {
        while (!isOnTrain) {
           // currentStation.station_wait_for_train();
            System.out.println("I'm izzz weytingggg");
        }
    }

    public void startThread() {
        System.out.println("Thread for Robot #" + id + " has been started.");
        t.start();
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Robot.id = id;
    }

    public int getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(int arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public int getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(int departureStation) {
        this.departureStation = departureStation;
    }

    public boolean getIsOnTrain() {
        return isOnTrain;
    }

    public void setIsOnTrain(boolean isOnTrain) {
        this.isOnTrain = isOnTrain;
    }

}
