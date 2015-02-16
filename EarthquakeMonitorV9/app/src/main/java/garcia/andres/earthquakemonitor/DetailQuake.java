package garcia.andres.earthquakemonitor;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by Andrés on 14/02/2015.
 */

public class DetailQuake extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earth_quake_detail);
        Bundle parameters = getIntent().getExtras();

        TextView Detail = (TextView) findViewById(R.id.tv_detail1);
        TextView detailTitle = (TextView) findViewById(R.id.tv_detail_title);
        ImageView Icon = (ImageView) findViewById(R.id.iv_icon_detail);
        Icon.setImageResource(parameters.getInt("Icon"));
        String detail;

        detail =  "The earthquake was located at " + parameters.getString("place") + ".\n";
        detail += "Its magnitude was " + parameters.getDouble("mag") + ".\n";
        detail += "The earthquake occurred on " + getDateMillis(parameters.getLong("time")) + " hours.\n";
        detail += "Its location was given by latitude of " + parameters.getDouble("latitude") +
                " and longitude of "+ parameters.getDouble("longitude")+
                " with a depth of "+ parameters.getDouble("depth")+ " km.\n";

        Detail.setText(detail);
        detailTitle.setText(parameters.getString("titleQ"));

        mapPositioning(parameters.getDouble("latitude"),parameters.getDouble("longitude"),
                parameters.getString("place"), parameters.getDouble("mag"), parameters.getInt("pin"));

    }

    public void mapPositioning(Double latitude, Double longitude, String place, Double mag , int pin){
        android.support.v4.app.FragmentManager fragment_manager = getSupportFragmentManager();
        android.support.v4.app.Fragment mapFragment = fragment_manager.findFragmentById(R.id.map_fragment);
        SupportMapFragment supportmapfragment = (SupportMapFragment)mapFragment;
        GoogleMap detailMap = supportmapfragment.getMap();
        detailMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(place)
                .snippet("Magnitude: " + mag.toString())
                .icon(BitmapDescriptorFactory.fromResource(pin)));
        detailMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 7.0f));

    }

    private String getDateMillis(Long time){
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss");
        return formatter.format(date);
    }
}
