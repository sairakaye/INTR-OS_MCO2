package components;
import java.util.ArrayList;

public class Train implements Runnable {
    private static int id;
    private int capacity;
    private ArrayList<Robot> passengers;


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

    public void loadPassengers(){

    }

    public void unloadPassengers(){

    }
}
