package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 23/12/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectFragment extends Fragment {


    private ListView mListView;


    public ConnectFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

        mListView = (ListView) rootView.findViewById(R.id.comunidadesListView);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://la-espana-extrana.firebaseio.com/comunidades");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this.getActivity(),
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
                Log.e("Comunidades", model);
            }
        };

        mListView.setAdapter(firebaseListAdapter);


        return rootView;
    }

}