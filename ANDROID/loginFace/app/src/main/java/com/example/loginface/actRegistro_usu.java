package com.example.loginface;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class actRegistro_usu extends AppCompatActivity {


    EditText nombUsu, apellidoUsu, telUsu, contraUsu, confContraUsu;
    TextView resultado2;
    Button btnEnv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro_usu);

      /*  nombUsu = findViewById(R.id.txtUsu);
        apellidoUsu = findViewById(R.id.txtApellido);
        telUsu = findViewById(R.id.txtTel);
        contraUsu = findViewById(R.id.txtPass);
        confContraUsu = findViewById(R.id.txtConfPass);*/
        resultado2 = findViewById(R.id.txtResult2);
        btnEnv = findViewById(R.id.btnEnv);

        btnEnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new   post_DatosUsu().execute("http://localhost:3000/buyer");
            }
        });

    }
}


