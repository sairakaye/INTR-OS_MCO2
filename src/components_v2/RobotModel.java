package components_v2;

public class RobotModel {

    RobotModel(int id, int departureStation, int arrivalStation) {
        this.id = id;
        this.departureStation = departureStation + 1;
        this.arrivalStation = arrivalStation + 1;
        this.riding = false;
    }

    public int getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(int departureStation) {
        this.departureStation = departureStation;
    }

    public int getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(int arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public boolean isRiding() {
        return riding;
    }

    public void setRiding(boolean riding) {
        this.riding = riding;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    private int id;
    private int departureStation;
    private int arrivalStation;
    private boolean riding;
}
