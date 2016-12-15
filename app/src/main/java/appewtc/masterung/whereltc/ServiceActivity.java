package appewtc.masterung.whereltc;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {
    //Explicit
    private GoogleMap mMap;
    private LocationManager locationrManager;
    private Criteria criteria;
    private double latADouble, lngADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        //Setup
        locationrManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    } // Main method

    @Override
    protected void onResume() {
        super.onResume();

        latADouble = 17.969893;
        lngADouble = 102.612508;
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);

        if (networkLocation!=null) {

            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();


        }
        Location gpsLocation = myFindLocation(locationrManager.GPS_PROVIDER);

        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();

        }

        Log.d("15decV1", "lat ==>" + latADouble);
        Log.d("15decV1","long ==>"+lngADouble);


    }//onResume

    @Override
    protected void onStop() {
        super.onStop();

        locationrManager.removeUpdates(locationListener);

    }

    public Location  myFindLocation(String strProvider) {

        Location location=null;

        if (locationrManager.isProviderEnabled(strProvider)) {

            locationrManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);


        } else {
        }


        return location;
    }


    public android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(latADouble, lngADouble);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        myAddMarker(latADouble, lngADouble);


    }// On maps

    private void myAddMarker(double latADouble, double lngADouble) {

        LatLng latLng = new LatLng(latADouble, lngADouble);
        mMap.addMarker(new MarkerOptions().position(latLng));

    }
}// main class
