package com.juarezserver.laespanaextrana;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by modes on 24/12/2016.
 */

public class laespanaextrana extends Application {



    public void OnCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
