package components;
import java.util.ArrayList;

//8 Stations total
public class Station {
    private int id;
    private ArrayList<Robot> waitingList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // reduce passengers in waiting list (train arrived)
    public void reduceWaiting(){

    }
}
