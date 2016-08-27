package com.myapp.glenwin.sproouut;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.myapp.glenwin.sproouut.fragments.HomeFragment;
import com.myapp.glenwin.sproouut.fragments.PlantATreeFragment;
import com.myapp.glenwin.sproouut.fragments.UpdatePromoFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback,LocationListener {
    SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    String mylocation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportMapFragment = SupportMapFragment.newInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame_for_fragments,new HomeFragment()).commit();
        getSupportActionBar().setTitle("Home");

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = "";
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentManager mapFragmentManager = getSupportFragmentManager();
        if(supportMapFragment.isAdded())
            mapFragmentManager.beginTransaction().hide(supportMapFragment).commit();
        if(id == R.id.nav_home){
            fragmentManager.beginTransaction().replace(R.id.content_frame_for_fragments,new HomeFragment()).commit();
            title = "Home";
        }
        else if (id == R.id.nav_tree) {
            if(!supportMapFragment.isAdded())
                mapFragmentManager.beginTransaction().add(R.id.maps,supportMapFragment).commit();
            else
                mapFragmentManager.beginTransaction().show(supportMapFragment).commit();
            title = "Plant a Tree";
        } else if (id == R.id.nav_age) {
            title = "Measure Age of Tree";
        } else if (id == R.id.nav_report) {
            title = "Report";
        }
        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final String[] warning_array_places = new  String[]{"Bicol Natural Park (Camarines Norte)", "Bongsanglay Natural Park (Masbate)",
                "Kalbario-Patapat Natural Park (Ilocos Norte)", "Mahagnao Volcano (Leyte Island)", "Mayon Volcano Natural Park (Albay)",
                "Mount Balatukan (Mindanao)", "Mount Apo (Mindanao)", "Mount Guiting-Guiting (Sibuyan Island)"};

        final Double[] warning_array_latitude = new Double[] {13.937686, 12.357435, 18.570539, 10.874444, 13.222781, 8.770000, 6.987456, 12.413889};

        final Double[] warning_array_longitude = new Double[]{123.010740, 123.550408, 120.899370, 124.859664, 123.753496, 124.98, 125.270996, 122.567778};

        final String[] danger_array_places = new String[]{"Manleluag Spring (Pangasinan)",
                "Mati (Davao Oriental)", "Mounts Banahaw–San Cristobal (Laguna)", "Mount Mantalingajan (Palawan)",
                "Mount Matutum (Mindanao)", "Mount Timolan (Zamboanga del Sur)", "Pamitinan Cave (Rodriguez)", "Quirino", "Quezon",
                "Rajah Sikatuna (Bohol)"};

        final Double[] danger_array_latitude = new Double[]{15.719222, 6.943763, 14.205828, 8.818333, 6.357708, 7.797568, 14.731888, 16.270042, 14.031391, 9.654637};
        final Double[] danger_array_longitude = new Double[]{120.316989, 126.246748, 121.167238, 117.675000, 125.071850, 123.241796, 121.190143, 121.537000, 122.113091, 123.869890};


        final String[] stable_array_places = new String[]{ "Mount Isarog (Luzon)",
                "Mount Kalatungan (Mindanao)", "Canlaon Volcano (Negros Island)", "Mount Kitanglad (Bukidnon Province)",
                "Mount Malindang (Misamis Occidental)", "Northwest Panay Peninsula Natural Park (Island of Panay)",
                "Sibalom Natural Park (Antique)", "Chocolate Hills (Carmen, Bohol)", "Pamitinan Cave (Rodriguez, Rizal)",
                "Apo Reef (Mindoro)", "Balinsasayao Twin Lakes (Negros Oriental)", "Bulusan Volcano Natural Park (Sorsogon)",
                "Pasonanca Natural Park (Zamboanga)", "Bessang Pass Natural Monument (Ilocos Sur)", "Mount Hibok-Hibok (Camiguin)",
                "Baganga Protected Landscape (Davao Oriental)", "Bigbiga Protected Landscape (Ilocos Sur)",
                "Buenavista Protected Landscape (Quezon)", "Central Cebu Protected Landscape (Cebu City)",
                "Dinadiawan River (Aurora)", "Rizal Park and Shrine (Dapitan)", "Lidlidda–Banayoyo (Ilocos Sur)",
                "Magapit (Cagayan)", "Mainit Hot Springs (Compostela Valley)" };


        final Double[] stable_array_latitude = new Double[] {13.659167, 7.955000, 10.411592, 8.139812, 8.217500, 11.808859, 10.765728, 9.839897, 14.731888, 12.675833, 9.353972,
                12.769167, 6.951045, 16.956944, 9.200556, 7.632910, 17.186531, 13.624299, 10.294734, 16.092557, 8.667118, 17.241421, 18.137369,
                7.400664};
        final Double[] stable_array_longitude = new Double[] {123.373333, 124.8025,
                123.133044, 124.916589, 123.636667, 121.970062, 122.140541, 124.197688, 121.190143, 120.475000, 123.179358, 124.056667, 122.074332,
                120.649722, 124.668056, 126.536683, 120.732536, 122.401419, 123.854447, 121.754809, 123.417331, 120.543102, 121.670502, 126.029792,
        };



        final ArrayAdapter<String> adapter;



        for(int counterStable = 0; counterStable < stable_array_places.length; counterStable++){

            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.stablelevel);
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            mMap.addMarker(new MarkerOptions()
                    .position (new LatLng(stable_array_latitude[counterStable],stable_array_longitude[counterStable]))
                    .title(stable_array_places[counterStable])
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(MainActivity.this, "Clickable :)", Toast.LENGTH_LONG).show();
                    //dito mo lagay glen :)
                    return false;
                }
            });
        }

        for(int counterWarning = 0; counterWarning < warning_array_places.length; counterWarning++){

            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.warninglevel);
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            mMap.addMarker(new MarkerOptions()
                    .position (new LatLng(warning_array_latitude[counterWarning],warning_array_longitude[counterWarning]))
                    .title(warning_array_places[counterWarning])
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(MainActivity.this, "Clickable :)", Toast.LENGTH_LONG).show();
                    //dito mo lagay glen :)
                    return false;
                }
            });
        }

        for(int counterDanger = 0; counterDanger < danger_array_places.length; counterDanger++){

            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.dangerlevel);
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            mMap.addMarker(new MarkerOptions()
                    .position (new LatLng(danger_array_latitude[counterDanger],danger_array_longitude[counterDanger]))
                    .title(stable_array_places[counterDanger])
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(MainActivity.this, "Clickable :)", Toast.LENGTH_LONG).show();
                    //dito mo lagay glen :)
                    return false;
                }
            });
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

}
