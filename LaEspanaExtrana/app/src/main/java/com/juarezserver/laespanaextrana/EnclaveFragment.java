package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 23/12/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.firebase.client.Query;
import com.firebase.ui.database.FirebaseListAdapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.util.Map;


public class EnclaveFragment extends Fragment {



    private String enclave;

    private RecyclerView mListaEnclaves;
    private DatabaseReference mDatabase;

    public void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Enclave, EnclavesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Enclave, EnclavesViewHolder>(
                Enclave.class,
                R.layout.enclave_row,
                EnclavesViewHolder.class,
                mDatabase.orderByChild("Comunidad_enclave").equalTo(enclave)


        )
        {


            protected void populateViewHolder(EnclavesViewHolder viewHolder, Enclave model, int position) {

                viewHolder.setTitulo(model.getNombre_enclave());
                viewHolder.setDescripcion(model.getDescripcion_enclave());
                viewHolder.setImage(getActivity().getApplicationContext()  , model.getImagen_Enclave());


            }
        };
        mListaEnclaves.setAdapter(firebaseRecyclerAdapter);
    }


    public static class EnclavesViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public EnclavesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setTitulo(String titulo){
            TextView titulo_enclave = (TextView) mView.findViewById(R.id.titulo_enclave);
            titulo_enclave.setText(titulo);
        }
        public void setDescripcion(String descripcion){
            TextView descripcion_enclave = (TextView) mView.findViewById(R.id.descripcion_enclave);
            descripcion_enclave.setText(descripcion);
        }

        public void setImage(Context ctx, String imagen){
            ImageView imagen_enclave = (ImageView) mView.findViewById(R.id.imagen_enclave);
            Picasso.with(ctx).load(imagen).into(imagen_enclave);

        }
    }
    public EnclaveFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        Bundle bundle = getArguments();
        if(bundle != null){
            enclave = bundle.getString("KEY_DETAIL", "Madrid");
            Log.d("Enclave recibido",enclave);
        }

        View rootView = inflater.inflate(R.layout.enclave_connect, container, false);

        mListaEnclaves = (RecyclerView) rootView.findViewById(R.id.lista_enclaves);
        mListaEnclaves.setHasFixedSize(true);
        mListaEnclaves.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Enclaves");










        return rootView;
    }




}