package components;

public class Robot {
    private static int id;
    private int arrivalStation;
    private int departureStation;

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
