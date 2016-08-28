package com.myapp.glenwin.sproouut;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.myapp.glenwin.sproouut.api.GPSTracker;
import com.myapp.glenwin.sproouut.host.ReturnHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class Report2 extends AppCompatActivity {
    double latitude;
    double longitude;
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String urladdReport = "http://" + host + "/forest/reportHere.php";
    String result;
    String message;
    String status;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);
        Intent intent = getIntent();
        gps = new GPSTracker(getBaseContext());

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        if(intent.getStringExtra("status").equals("0")){
            status = "0";
        }
        else if(intent.getStringExtra("status").equals("1")){
            status = "1";
        }
        else{
            status = "2";
        }
        String predefined = "I'd like to report a ";
        if(status.equals("0")){
            predefined += "stable status at "+latitude+","+longitude;
        }
        else if(status.equals("1")){
            predefined += "warning status at "+latitude+","+longitude;
        }
        else{
            predefined += "danger status at "+latitude+","+longitude;
        }
        message = predefined;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("639059242742", null, message, null, null);
        Toast.makeText(getApplicationContext(),"Successfully send",Toast.LENGTH_SHORT).show();
        addReport();

    }
    ProgressDialog pDialog;
    public void addReport(){
        class TreeFetch extends AsyncTask<String, Void, String> {

            /**
             * Before starting background thread Show Progress Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(Report2.this);
                pDialog.setMessage("Adding Request...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            /**
             * getting All products from url
             * */
            protected String doInBackground(String... args) {
                BufferedReader bufferedReader;

                try {
                    URL url = new URL(urladdReport+"?mess="+message+"&lat="+latitude+"&lon="+longitude+"&sta="+status);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    Log.e("log_tag", "Error converting result" + e.toString());
                }
                return result;
            }

            /**
             * After completing background task Dismiss the progress dialog
             * **/
            protected void onPostExecute(String file_url) {
                // dismiss the dialog after getting all products
                pDialog.dismiss();
                startActivity(new Intent(Report2.this,MainActivity.class));
                finish();

            }
        }
        TreeFetch treeFetch = new TreeFetch();
        treeFetch.execute();
    }


}
