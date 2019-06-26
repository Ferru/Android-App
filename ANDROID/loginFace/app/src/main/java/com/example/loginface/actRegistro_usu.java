package com.example.loginface;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class actRegistro_usu extends AppCompatActivity{


    EditText nombUsu, apellidoUsu, telUsu, contraUsu, confContraUsu;
    Button btnEnv;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro_usu);

        nombUsu = findViewById(R.id.txtUsu);
        apellidoUsu = findViewById(R.id.txtApellido);
        telUsu = findViewById(R.id.txtTel);
        contraUsu = findViewById(R.id.txtPass);
        confContraUsu = findViewById(R.id.txtConfPass);
        btnEnv = findViewById(R.id.btnEnv);

        btnEnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    new PostDatosUsu_Act().execute("http://localhost:3000/buyer");
            }
        });

    }
}
