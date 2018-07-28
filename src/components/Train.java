package components;
import java.util.ArrayList;

public class Train implements Runnable {
    private int id;
    private int capacity;
    private static int numTrains;
    private ArrayList<Robot> passengers;
    private boolean running; //running or waiting
    private Station curStation;

    public Train(int capacity){
        numTrains++;
        this.id = numTrains;
        this.capacity = capacity;
        running = true;
    }

    @Override
    public void run() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Robot> getPassengers(){
        return passengers;
    }

    public int getNumberOfPassengers(){
        return passengers.size();
    }

    public void loadTrain(Robot passenger){
        if (!isFull())
            passengers.add(passenger);
    }

    public void unloadTrain(){
        for (int i = 0; i < passengers.size(); i++){
            if (passengers.get(i).getDepartureStation().getId() == curStation.getId()) {
                passengers.remove(i);
                passengers.trimToSize();
            }
        }
    }

    public boolean isFull(){
        return capacity == passengers.size();
    }
}
