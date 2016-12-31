package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 23/12/2016.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnclavesFragment extends Fragment {


    private ListView mListView;
    private String comunidad;

    private ProgressDialog mProgress;

    public EnclavesFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle = getArguments();
        if(bundle != null){
            comunidad = bundle.getString("KEY_DETAIL", "Madrid");
            Log.d("Comunidad pasada",comunidad);
        }

        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

        mListView = (ListView) rootView.findViewById(R.id.comunidadesListView);

        mProgress = new ProgressDialog(this.getActivity());
        mProgress.setMessage("Cargando Enclaves en "+comunidad);
        mProgress.show();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://la-espana-extrana.firebaseio.com/Enclaves");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this.getActivity(),
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference.orderByChild("Comunidad_enclave").equalTo(comunidad)
        ) {
            @Override
            protected String parseSnapshot(DataSnapshot snapshot) {
                Log.d("imagen recbida", snapshot.child("Imagen_Enclave").getValue(String.class));
                return snapshot.child("Nombre_enclave").getValue(String.class);
            }

            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
                mProgress.dismiss();
            }
        };
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topic = String.valueOf(parent.getItemAtPosition(position));

              Log.d("Comunidad kkkk",topic);

                //PASA VALOR SELECCIONADO AL SIGUIENTE FRAGMENT
                EnclaveFragment myDetailFragment = new EnclaveFragment();
                Bundle bundle = new Bundle();
                bundle.putString("KEY_DETAIL", topic);
                myDetailFragment.setArguments(bundle);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, myDetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        mListView.setAdapter(firebaseListAdapter);


        return rootView;
    }




}