package com.system.perfect.moviecatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private Button btnCari;
    EditText strCari;

    private RequestQueue mRequest;
    private StringRequest sRequest;
    private String url = "http://www.mocky.io/v2/5c3e81473500005a003e98c3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCari = (Button)findViewById(R.id.btn_cari);
        strCari = (EditText)findViewById(R.id.str_search);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_film = strCari.getText().toString().trim();
                requestFilm();
            }
        });
    }

    private void requestFilm() {
        // Inisiasi daftar request
        mRequest = Volley.newRequestQueue(this);

        // Inisiasi string request
        sRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error : " + error.toString());
            }
        });

        // Add String request
        mRequest.add(sRequest);
    }
}
