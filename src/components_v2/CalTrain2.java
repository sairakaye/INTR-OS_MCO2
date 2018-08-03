package components_v2;

public class CalTrain2 {
    Station[] stations = new Station[8];
    Train trains[] = new Train[16];
    GUI gui = new GUI(this);

    public synchronized void StationStatus(){
        for(int nCtr = 0; nCtr < 8; nCtr++){
            try{
                gui.Output.append("Station " + stations[nCtr].getStationID() +" - Train "+stations[nCtr].getCurrTrain().getTrainID() + " | ");
            }catch(Exception ex){}
        }
        gui.Output.append("\n");
    }
    public void init(){
        for(int nCtr = 0; nCtr < 8; nCtr++)
            stations[nCtr] = new Station(nCtr);

        for(int nCtr = 0; nCtr < 16; nCtr++)
            trains[nCtr] = new Train(nCtr, stations, 20, this);

        gui.setVisible(true);
    }
}
