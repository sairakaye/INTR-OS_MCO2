package components;

public class Robot implements Runnable {
    private int id = 0;
    private static int totalRobots;
    private Station arrivalStation; // which station passenger rode
    private Station departureStation; // which station passenger is going to
    private volatile boolean isOnTrain;
    private Station currentStation;
    private Thread t;

    public Robot(Station currentStation, Station arrivalStation, Station departureStation) {
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.currentStation = currentStation;
        totalRobots++;
        this.id = totalRobots;
        isOnTrain = false;
        t = new Thread(this);
    }

    // For testing purposes.
    public Robot(int id) {
        t = new Thread(this);
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public boolean getIsOnTrain() {
        return isOnTrain;
    }

    public void setIsOnTrain(boolean isOnTrain) {
        this.isOnTrain = isOnTrain;
    }
}
