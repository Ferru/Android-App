package com.example.loginface;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    CallbackManager cbManager;
    LoginButton btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbManager = CallbackManager.Factory.create();
        btnlogin = findViewById(R.id.faceb_Lbutton);

        btnlogin.registerCallback(cbManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Toast.makeText(getApplicationContext(), "Inicio correcto", Toast.LENGTH_SHORT).show();
                irNavDActivity();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Se cancelo", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void irNavDActivity(){

        Intent intent = new Intent(getApplicationContext(), navD_Activity.class);
        finish();
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cbManager.onActivityResult(requestCode,resultCode,data);
    }
}
