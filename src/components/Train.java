package components;
import java.util.ArrayList;

public class Train implements Runnable {
    private int id;
    private int capacity;
    private static int numTrains;
    private ArrayList<Robot> passengers;
    private ArrayList<Station> stations; //temp
    private boolean isRunning; //running or waiting
    private Station curStation;
    private Thread t;

    public Train(int capacity){

        // Testing
        ArrayList<Station> stations = new ArrayList<>();

        stations.add(new Station(0));
        stations.add(new Station(1));
        stations.add(new Station(2));

        numTrains++;
        this.id = numTrains;
        this.capacity = capacity;
        isRunning = true;

        t = new Thread(this);
    }

    @Override
    public void run() {
        int count = 0;

        System.out.println("The train is running....");

        while (isRunning) {
            try {
                t.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count < 3) {
                curStation = stations.get(count);
                count++;
            } else {
                curStation = stations.get(0);
                count = 0;
            }

            System.out.println("The train " + id + "is at " + curStation.getId() + "...");
        }

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
