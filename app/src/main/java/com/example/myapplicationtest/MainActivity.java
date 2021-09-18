package com.example.myapplicationtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;

    private EditText etName;
    private Button btnShowInfo;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NukeSSLCerts.nuke();
        Toast.makeText(this,"This is onCreate().", Toast.LENGTH_SHORT).show();

        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        btnShowInfo = findViewById(R.id.btnShowInfo);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showSaveMessage();
            }
        });

        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showInfo();
            }
        });
    }
    public void showInfo() {
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Toast.makeText(this, "This is onStart().", Toast.LENGTH_SHORT).show();
    }

    private void showSaveMessage(){
        Toast.makeText(getApplicationContext(),
           " save button pressed", Toast.LENGTH_SHORT).show();

        String url = "https://192.168.254.101/IPT102/insert/InsertStudent.php";

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        stringRequest = new StringRequest(Request.Method.POST,
                url, new  Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")){
                            Toast.makeText(getApplicationContext(), " Wassup, " +
                                    " your data has been successfully saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), " ERROR", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", etName.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void showInfo (View v){

    }

}
