package components;
import java.util.ArrayList;

public class Train implements Runnable {
    private static int id;
    private int capacity;
    private ArrayList<Robot> passengers;
    private boolean status; //running or waiting
    private Station curStation;

    public Train(int capacity){
        id++;
        this.capacity = capacity;
    }

    @Override
    public void run() {

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Train.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfPassengers(){
        return passengers.size();
    }

    public void loadPassengers(){

    }

    public void unloadPassengers(){

    }
}
