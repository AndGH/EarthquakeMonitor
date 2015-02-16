package garcia.andres.earthquakemonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import garcia.andres.earthquakemonitor.app.AppController;


/*
* minSdkVersion 15
* targetSdkVersion 21
*/

public class EarthQuakeMainActivity extends ActionBarActivity implements DataInterface{
    // json object response url
    String GeoJSONUrlFeed = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

    // Progress dialog
    private ProgressDialog loadingMessage;

    // Tittle of the summary view
    private TextView Summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake_main);

        Summary = (TextView) findViewById(R.id.tv_last_hour);

        // On loading notification settings
        loadingMessage = new ProgressDialog(this);
        loadingMessage.setMessage("Please wait...");
        loadingMessage.setCancelable(false);

        getJSONObjectReqFeed();
    }

    private void getJSONObjectReqFeed() {
        showLoading();

        // Create new JSON object request
        JsonObjectRequest earthquakeJSONObjectReq = new JsonObjectRequest(Request.Method.GET,
                GeoJSONUrlFeed, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Obtains all features from GeoJSON
                    // Type of every feature can be found in http://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php

                    //Creates new ArrayList to call the interface
                    ArrayList<QuakeStruct> quakesHour = new ArrayList<>();

                    JSONObject metadata = response.getJSONObject("metadata");
                    JSONArray features = response.getJSONArray("features");
                    String title = metadata.getString("title");

                    Summary.setText(title);

                    //Parse features from JSONArray
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject features_jsonobject = (JSONObject) features.get(i);

                        //String type_f = features_jsonobject.getString("type");
                        JSONObject properties = (JSONObject) features_jsonobject.get("properties");
                        JSONObject geometry = (JSONObject) features_jsonobject.get("geometry");
                        String Id = features_jsonobject.getString("id");

                        //Parse properties
                        Double mag = properties.getDouble("mag");
                        String place = properties.getString("place");
                        Long time = properties.getLong("time");
                        Long updated = properties.getLong("updated");
                        int tz = properties.getInt("tz");
                        String url_prop = properties.getString("url");
                        String detail = properties.getString("detail");
                        String felt = properties.getString("felt"); // int felt, sometimes null
                        String cdi = properties.getString("cdi"); // Double cdi, sometimes null
                        String mmi = properties.getString("mmi"); // Double mmi, sometimes null
                        String alert = properties.getString("alert");
                        String status_prop = properties.getString("status");
                        int tsunami = properties.getInt("tsunami");
                        int sig = properties.getInt("sig");
                        String net = properties.getString("net");
                        String code = properties.getString("code");
                        String ids = properties.getString("ids");
                        String sources = properties.getString("sources");
                        String types = properties.getString("types");
                        String nst = properties.getString("nst");
                        String dmin = properties.getString("dmin"); // Double dmin, sometimes null
                        String rms = properties.getString("rms"); // Double rms, sometimes null
                        String gap = properties.getString("gap"); // Double gap, sometimes null
                        String magType = properties.getString("magType");
                        String type_prop = properties.getString("type");
                        String titleQ = properties.getString("title");

                        //Parse geometry
                        JSONArray coordinates = geometry.getJSONArray("coordinates");

                        //Parse coordinates
                        Double longitude = coordinates.getDouble(0);
                        Double latitude = coordinates.getDouble(1);
                        Double depth = coordinates.getDouble(2);

                        int Icon = R.drawable.magnitude1;
                        int bColor = R.color.green;
                        int pin = R.drawable.magnitude1pin;

                        //Set the correct resource for each case
                        if (mag < 1){
                            Icon = R.drawable.magnitude1;
                            bColor = R.color.green;
                            pin = R.drawable.magnitude1pin;
                        }
                        else if (mag >= 1 && mag < 3.5) {
                            Icon = R.drawable.magnitude2;
                            bColor = R.color.greenyellow;
                            pin = R.drawable.magnitude2pin;
                        }
                        else if (mag >= 3.5 && mag < 5){
                            Icon = R.drawable.magnitude3;
                            bColor = R.color.yellow;
                            pin = R.drawable.magnitude3pin;
                        }
                        else if (mag >= 5 && mag < 9){
                            Icon = R.drawable.magnitude4;
                            bColor = R.color.orange;
                            pin = R.drawable.magnitude4pin;
                        }
                        else if (mag >= 9){
                            Icon = R.drawable.magnitude5;
                            bColor = R.color.darkred;
                            pin = R.drawable.magnitude5pin;
                        }

                        quakesHour.add(new QuakeStruct(Icon, title, mag, place, time, updated, tz,
                                url_prop, detail, felt, cdi, mmi, alert, status_prop, tsunami, sig,
                                net, code, ids, sources, types, nst, dmin, rms, gap, magType,
                                type_prop, latitude, longitude, depth, Id, bColor, pin, titleQ));

                        // Calls the interface to refresh ths summary map
                        onQuakeFound(latitude, longitude, place, mag, pin);

                    }

                    //Displays ListView from interface
                    onDataReceived(quakesHour);
                    //Sets the position of the summary map
                    setSummaryDefaultPosition();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + "Unable to recover JSONobject from GeoJSON",
                            Toast.LENGTH_LONG).show();
                }
                hideLoading();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "There is no internet connection.",
                        Toast.LENGTH_LONG).show();
                // hide the progress dialog
                hideLoading();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(earthquakeJSONObjectReq);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_earth_quake_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            getJSONObjectReqFeed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Popups
    private void showLoading() {
        if (!loadingMessage.isShowing()) {
            loadingMessage.show();
        }
    }

    private void hideLoading() {
        if (loadingMessage.isShowing()){
            loadingMessage.dismiss();
        }
    }

    //Create an abstract method to receive the information from the interface
    @Override
    public void onDataReceived(final ArrayList<QuakeStruct> QuakesInterface) {
        ListView list = (ListView) findViewById(R.id.lst_list);
        list.setAdapter(new ListAdapter(this, R.layout.list_element, QuakesInterface){
            @Override
            public void onInput(Object element, View view) {
                TextView tv_mag = (TextView) view.findViewById(R.id.tv_magnitude);
                tv_mag.setText("Magnitude: " + ((QuakeStruct) element).getMag().toString());

                TextView tv_place = (TextView) view.findViewById(R.id.tv_place);
                tv_place.setText("PLace: " + ((QuakeStruct) element).getPlace());

                TextView tv_row_title = (TextView) view.findViewById(R.id.tv_summary_title);
                tv_row_title.setText(((QuakeStruct) element).getTitleQ());

                ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                iv_icon.setImageResource(((QuakeStruct) element).getIcon());

                LinearLayout linear = (LinearLayout) view.findViewById(R.id.ln_element);
                linear.setBackgroundResource(((QuakeStruct) element).getbColor());
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             QuakeStruct selected = (QuakeStruct) parent.getItemAtPosition(position);
             Intent Detail = new Intent(EarthQuakeMainActivity.this,DetailQuake.class);
                Detail.putExtra("Icon",selected.getIcon());
                Detail.putExtra("Title",selected.getTitle());
                Detail.putExtra("mag",selected.getMag());
                Detail.putExtra("place",selected.getPlace());
                Detail.putExtra("time",selected.getTime());
                Detail.putExtra("updated",selected.getUpdated());
                Detail.putExtra("tz", selected.getTz());
                Detail.putExtra("url", selected.getUrl());
                Detail.putExtra("detail", selected.getDetail());
                Detail.putExtra("felt", selected.getFelt());
                Detail.putExtra("cdi", selected.getCdi());
                Detail.putExtra("mmi", selected.getMmi());
                Detail.putExtra("alert", selected.getAlert());
                Detail.putExtra("status", selected.getStatus());
                Detail.putExtra("tsunami", selected.getTsunami());
                Detail.putExtra("sig", selected.getSig());
                Detail.putExtra("net", selected.getNet());
                Detail.putExtra("code", selected.getCode());
                Detail.putExtra("ids", selected.getIds());
                Detail.putExtra("sources", selected.getSources());
                Detail.putExtra("types", selected.getTypes());
                Detail.putExtra("nst", selected.getNst());
                Detail.putExtra("dmin", selected.getDmin());
                Detail.putExtra("rms", selected.getRms());
                Detail.putExtra("gap", selected.getGap());
                Detail.putExtra("magType", selected.getMagType());
                Detail.putExtra("type", selected.getType());
                Detail.putExtra("latitude", selected.getLatitude());
                Detail.putExtra("longitude", selected.getLongitude());
                Detail.putExtra("depth", selected.getDepth());
                Detail.putExtra("Id", selected.getID());
                Detail.putExtra("bColor", selected.getbColor());
                Detail.putExtra("pin", selected.getPin());
                Detail.putExtra("titleQ", selected.getTitleQ());
                startActivity(Detail);

            }
        });
    }

    // QuakeFound from interface
    @Override
    public void onQuakeFound(Double latitude, Double longitude, String place, Double mag, int pin) {
        mapSummary(latitude, longitude, place, mag, pin);
    }

    //Summary map of the quakes
    public void mapSummary(Double latitude, Double longitude, String place, Double mag , int pin){
        android.support.v4.app.FragmentManager fragment_manager = getSupportFragmentManager();
        android.support.v4.app.Fragment mapFragment = fragment_manager.findFragmentById(R.id.map_fragment_summary);
        SupportMapFragment supportmapfragment = (SupportMapFragment)mapFragment;
        GoogleMap supportMapPins = supportmapfragment.getMap();
        supportMapPins.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(place)
                .snippet("Magnitude: " + mag.toString())
                .icon(BitmapDescriptorFactory.fromResource(pin)));
    }

    //Set the position in north america
    public void setSummaryDefaultPosition(){
        android.support.v4.app.FragmentManager fragment_manager = getSupportFragmentManager();
        android.support.v4.app.Fragment mapFragment = fragment_manager.findFragmentById(R.id.map_fragment_summary);
        SupportMapFragment supportmapfragment = (SupportMapFragment)mapFragment;
        GoogleMap summaryMap = supportmapfragment.getMap();
        summaryMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.3655804, -97.626397), 3.0f));
    }
}
