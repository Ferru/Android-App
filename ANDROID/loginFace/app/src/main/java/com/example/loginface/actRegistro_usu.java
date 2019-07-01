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



public class actRegistro_usu extends AppCompatActivity{


    EditText nombUsu, apellidoUsu, telUsu, contraUsu, confContraUsu, Usuario;
    Button btnEnv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro_usu);

        nombUsu = findViewById(R.id.txtNombreEd);
        apellidoUsu = findViewById(R.id.txtApellido);
        telUsu = findViewById(R.id.txtTel);
        Usuario = findViewById(R.id.txtUsu);

        contraUsu = findViewById(R.id.txtPass);
        confContraUsu = findViewById(R.id.txtConfPass);
        btnEnv = findViewById(R.id.btnEnv);

        btnEnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                consumirServicio();
            }
        });

    }

    public void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String nUsu =  nombUsu .getText().toString();
        String apUsu= apellidoUsu.getText().toString();
        String telU = telUsu.getText().toString();
        String Usu = Usuario.getText().toString();
        String passUsu =  contraUsu.getText().toString();



       PostDatosUsu_Act postDatosUsu_act = new PostDatosUsu_Act(this,"http//localhost:3000",nUsu,apUsu,telU, Usu, passUsu);
        postDatosUsu_act.execute();



    }
}
