package com.myapp.glenwin.sproouut.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myapp.glenwin.sproouut.R;
import com.myapp.glenwin.sproouut.host.ReturnHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Glenwin18 on 7/10/2016.
 */
public class UpdatePromoFragment extends Fragment {
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String validate_url = "http://"+host+"/forest/validateCode.php";
    ProgressDialog pDialog;
    EditText codeEnter;
    String result = "";
    TextView tvStat;

    public UpdatePromoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validate_event, container, false);
        codeEnter = (EditText)rootView.findViewById(R.id.editCode);
        tvStat = (TextView)rootView.findViewById(R.id.tvStat);
        Button btnClick = (Button)rootView.findViewById(R.id.btnRedeem);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateACode();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void validateACode(){
        class ValidateCode extends AsyncTask<String, Void, String> {

            /**
             * Before starting background thread Show Progress Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Redeeming points...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            /**
             * getting All products from url
             * */
            //url+"?event_code="+code
            protected String doInBackground(String... args) {
                String code = String.valueOf(codeEnter.getText());
                BufferedReader bufferedReader;

                try {
                    URL url = new URL(validate_url+"?event_code="+code);
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
                getCode();
            }


        }
        ValidateCode validateMyCode = new ValidateCode();
        validateMyCode.execute();
    }
    public void getCode(){
        String stat = "";
        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject c = jArray.getJSONObject(i);
                stat = c.getString("status");
            }
            tvStat.setText(stat.equals("1") ? "Good" : "Bad");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
