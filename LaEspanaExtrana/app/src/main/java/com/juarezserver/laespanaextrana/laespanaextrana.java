package com.juarezserver.laespanaextrana;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.firebase.FirebaseApp.getApps;

/**
 * Created by modes on 24/12/2016.
 */

public class laespanaextrana extends Application {



    public void OnCreate(){
        super.onCreate();



    if(!FirebaseApp.getApps(this).isEmpty()){

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        }
    }
}
