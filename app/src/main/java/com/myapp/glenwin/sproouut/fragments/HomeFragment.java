package com.myapp.glenwin.sproouut.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.myapp.glenwin.sproouut.R;
import com.myapp.glenwin.sproouut.adapter.CustomListAdapter;
import com.myapp.glenwin.sproouut.api.JSONObjectRequest;
import com.myapp.glenwin.sproouut.host.ReturnHost;
import com.myapp.glenwin.sproouut.utils.Preferences;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Glenwin18 on 8/27/2016.
 */
public class HomeFragment extends Fragment {
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String urlForGettingWeatherCondition = "http://api.openweathermap.org/data/2.5/weather?q=Los%20Ba%C3%B1os,Philippines&APPID=73caff86339604776803ac447986a99c";
    private String urlforWeatherIcon = "http://openweathermap.org/img/w/";
    private String urlForTree = "http://"+host+"/forest/trees/";
    private String urlForFetchTrees = "http://"+host+"/forest/getBoughtTrees.php";
    ProgressDialog pDialog;
    GridView list;
    String result;
    TextView tvCityCountry,tvDescription,tvHumidity,tvTemperature,tvPressure,tvCurrentPoints;
    ImageView ivWeatherIcon;
    private RequestQueue queue;
    private static final String TAG = HomeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if(Preferences.ifHasPoints(getActivity()) == false){
            Preferences.putDefaultPoints(getActivity());
        }
        list = (GridView) rootView.findViewById(R.id.gridview);
        tvCityCountry = (TextView)rootView.findViewById(R.id.tvCityCountry);
        tvDescription = (TextView)rootView.findViewById(R.id.tvDescription);
        tvHumidity = (TextView)rootView.findViewById(R.id.tvHumidity);
        tvTemperature = (TextView)rootView.findViewById(R.id.tvTemperature);
        tvPressure = (TextView)rootView.findViewById(R.id.tvPressure);
        ivWeatherIcon = (ImageView)rootView.findViewById(R.id.ivWeatherIcon);
        tvCurrentPoints = (TextView)rootView.findViewById(R.id.currentPoints);
        queue = Volley.newRequestQueue(getActivity());

        getWeatherCondition();
        return rootView;

    }
    private void getWeatherCondition(){
        JSONObjectRequest authRequest = new JSONObjectRequest(Request.Method.GET, urlForGettingWeatherCondition, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: " + error);

            }
        }, getActivity());
        authRequest.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(authRequest);
    }

    private void parseData(JSONObject response){
        try{

            JSONArray jArray = response.getJSONArray("weather");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                tvCityCountry.setText("Los Baños,Laguna Philippines");
                tvDescription.setText(json_data.getString("main")+"("+json_data.getString("description")+")");
                Picasso.with(getActivity())
                        .load(urlforWeatherIcon+json_data.getString("icon")+".png")
                        .into(ivWeatherIcon);
            }
            JSONObject main = response.getJSONObject("main");
            tvHumidity.setText("Humidity : " +main.getString("humidity")+"%");
            tvTemperature.setText("Temperature : " +String.format("%.2f", main.getDouble("temp") - 273.15)+ " ℃");
            tvPressure.setText("Pressure : " + main.getString("pressure") + " hPa");
            tvCurrentPoints.setText("Tree Points : " + Preferences.getTreePoints(getActivity()));

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
