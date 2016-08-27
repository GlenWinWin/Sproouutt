package com.myapp.glenwin.sproouut.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.myapp.glenwin.sproouut.R;
import com.myapp.glenwin.sproouut.adapter.CustomListAdapter;
import com.myapp.glenwin.sproouut.host.ReturnHost;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Glenwin18 on 8/24/2016.
 */
public class PlantATreeFragment extends Fragment{
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String urlForTree = "http://"+host+"/forest/trees/";
    private String urlForFetchTrees = "http://"+host+"/forest/fetchForest.php";
    ProgressDialog pDialog;
    String result;
    GridView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_plant_a_tree, container, false);
        list = (GridView) rootView.findViewById(R.id.gridview);

        fetchTrees();
//        final ImageView imagelockTree = (ImageView)rootView.findViewById(R.id.ivTree);
//        final TextView tvTree = (TextView)rootView.findViewById(R.id.tvTree);
//        imagelockTree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
//                builder2.setTitle("");
//                builder2.setMessage("Do you want to buy the plant?");
//                builder2.setCancelable(true);
//                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog2, int id) {
//                        dialog2.cancel();
//                    }
//                });
//                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                        if(POINTS == 10){
//                            AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
//                            builder3.setTitle("Not enough points");
//                            builder3.setMessage("You don't have enough points!");
//                            builder3.setCancelable(true);
//                            builder3.setNegativeButton("Cancel",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog3, int id) {
//
//                                            dialog3.cancel();
//                                        }
//                                    });
//                            AlertDialog alert13 = builder3.create();
//                            alert13.show();
//                        }
//                        else if(POINTS >= 20){
//                            imagelockTree.setImageDrawable(getResources().getDrawable(R.drawable.tree));
//                            imagelockTree.setEnabled(false);
//                            tvTree.setText("Bought");
//                        }
//                    }
//                });
//                AlertDialog alert12 = builder2.create();
//                alert12.show();
//            }
//        });
        return rootView;
    }
    public void fetchTrees(){
        class TreeFetch extends AsyncTask<String, Void, String> {

            /**
             * Before starting background thread Show Progress Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Getting Trees...");
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
                    URL url = new URL(urlForFetchTrees);
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
                fetchallTrees();
            }
        }
        TreeFetch treeFetch = new TreeFetch();
        treeFetch.execute();
    }
    public void fetchallTrees(){
        try {

            JSONArray jArray = new JSONArray(result);
            ArrayList<String> treeNames = new ArrayList<>();
            ArrayList<String> treePoints = new ArrayList<>();
            ArrayList<String> treeLockPics = new ArrayList<>();
            for(int i=0;i<jArray.length();i++){
                JSONObject c = jArray.getJSONObject(i);
                treeNames.add(c.getString("tree_name"));
                treePoints.add(c.getString("point"));
                treeLockPics.add(urlForTree + c.getString("pic"));
            }
            CustomListAdapter adapter = new CustomListAdapter(getActivity(),treeNames,treePoints,treeLockPics);
            list.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
