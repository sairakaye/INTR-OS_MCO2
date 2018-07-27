package components;

public class Robot implements Runnable {
    private static int id = 0;
    private int arrivalStation; //which station passenger rode
    private int departureStation; //which station passenger is going to
    private boolean onTrain;
    private Station currentStation;

    private Robot(Station currentStation, int id, int arrivalStation, int departureStation) {
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.currentStation = currentStation;
        this.id = id;

        id++;
    }

    @Override
    public void run() {
        currentStation.station_wait_for_train();
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


}
