package com.example.json_sendandreceive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickGetInfo(View view)
    {
        try
        {
            TextView textview = (TextView)findViewById(R.id.inputAddressNumber);
            String text = textview.getText().toString();

            // url
            String url = "https://zip-cloud.appspot.com/api/search?zipcode="+text;

            mQueue = Volley.newRequestQueue(this);
            mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            // JSONObjectのパース、List、Viewへの追加等
                            ParseJSON(response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override public void onErrorResponse(VolleyError error)
                        {
                            Log.e("Error", "レスポンスエラー\n");
                            DispWetherInfoErr(error);
                        }
                    }));
        }
        catch(Exception e)
        {
            Log.e("Error", "例外発生\n" + e);
        }
    }

    public void ParseJSON(JSONObject response)
    {
        try
        {
            Log.d("Debug", "Response is: OK\n");

            // 住所１
            TextView textview = findViewById(R.id.outputAddress1);
            JSONArray datas = response.getJSONArray("results");
            for (int i = 0; i < datas.length(); i++) {
                JSONObject data = datas.getJSONObject(i);
                String address1 = data.getString("address1");
                textview.setText(address1);
            }

            // 住所２
            TextView textview2 = findViewById(R.id.outputAddress2);
            for (int i = 0; i < datas.length(); i++) {
                JSONObject data = datas.getJSONObject(i);
                String address2 = data.getString("address2");
                textview2.setText(address2);
            }

            // 住所3
            TextView textview3 = findViewById(R.id.outputAddress3);
            for (int i = 0; i < datas.length(); i++) {
                JSONObject data = datas.getJSONObject(i);
                String address3 = data.getString("address3");
                textview3.setText(address3);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void DispWetherInfoErr(VolleyError error)
    {
        TextView textview = findViewById(R.id.outputAddress1);
        textview.setText("Response is: NG\n" + error);
    }
}
