package com.example.loginface;

import android.app.Application;
import android.media.Image;
import android.support.v4.widget.DrawerLayout;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.ProfilePictureView;

public class login_clase extends Application {



    public void onCreate(){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);




    }
}
