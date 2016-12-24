package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 23/12/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ConnectFragment extends Fragment {

    private Button mSendData;
    private Firebase mRef;

    public ConnectFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);


        Firebase.setAndroidContext(this.getActivity());

        mRef = new Firebase("https://la-espana-extrana.firebaseio.com/");

        mSendData = (Button) rootView.findViewById(R.id.sendData);

        mSendData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

}