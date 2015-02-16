package garcia.andres.earthquakemonitor;

import java.util.ArrayList;

/*
 * Created by Andrés on 14/02/2015.
 */
public interface DataInterface {

    void onDataReceived(ArrayList<QuakeStruct> Quakes);
    void onQuakeFound(Double latitude, Double longitude, String place, Double mag, int pin);

}
