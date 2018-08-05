package components;

import java.util.ArrayList;

public class TrainSingleton {

    public TrainSingleton() {
        stations = new Station[8];
        robots = new ArrayList<>();
        trains = new ArrayList<>();
    }

    public Station[] getStations() {
        return stations;
    }

    public void setStations(Station[] stations) {
        this.stations = stations;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    private Station[] stations;
    private ArrayList<Robot> robots;
    private ArrayList<Train> trains;
}
