package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 23/12/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class ProponerEnclaveFragment extends Fragment {


    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;

    private EditText mNombreTxt;
    private EditText mDescripcionTxt;
    private EditText mPoblacionTxt;
    private EditText mProvinciaTxt;
    private EditText mEmailTxt;
    private EditText mUsuarioTxt;

    private Button mEnviarBtn;

    private Uri mImageUri = null;

    private StorageReference mStorage;

    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;

    public ProponerEnclaveFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            mStorage = FirebaseStorage.getInstance().getReference();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Enclaves");

        View rootView = inflater.inflate(R.layout.fragment_proponer_enclave, container, false);

        setHasOptionsMenu(true);


            mSelectImage = (ImageButton) rootView.findViewById(R.id.selectImageBtn);
            mNombreTxt = (EditText)  rootView.findViewById(R.id.nombreTxt);
            mDescripcionTxt = (EditText) rootView.findViewById(R.id.descripcionTxt);
        mProvinciaTxt = (EditText) rootView.findViewById(R.id.ProvinciaTxt);
        mPoblacionTxt = (EditText) rootView.findViewById(R.id.PoblacionTxt);
        mEmailTxt = (EditText) rootView.findViewById(R.id.emailTxt);
        mUsuarioTxt = (EditText) rootView.findViewById(R.id.usuarioTxt);



            mEnviarBtn = (Button) rootView.findViewById(R.id.enviarBtn);

            mProgress = new ProgressDialog(this.getActivity());


            mSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GALLERY_REQUEST);
                }
            });


            mEnviarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startPosting();
                }
            });
        return rootView;
    }


    public void startPosting(){


            final String nombre_val = mNombreTxt.getText().toString().trim();
            final String desc_val = mDescripcionTxt.getText().toString().trim();
        final String provincia_val = mProvinciaTxt.getText().toString().trim();
        final String poblacion_val = mPoblacionTxt.getText().toString().trim();
        final String usuario_val = mUsuarioTxt.getText().toString().trim();
        final String email_val = mEmailTxt.getText().toString().trim();

            if(!TextUtils.isEmpty(nombre_val) && !TextUtils.isEmpty(desc_val) && mImageUri !=null){

                mProgress.setMessage("Enviando enclave...");
                mProgress.show();


                StorageReference filePath = mStorage.child("Imagenes_Enclaves").child(mImageUri.getLastPathSegment());
                filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        DatabaseReference nuevoEnclave = mDatabase.push();

                        nuevoEnclave.child("Nombre_enclave").setValue(nombre_val);
                        nuevoEnclave.child("Descripcion_enclave").setValue(desc_val);
                        nuevoEnclave.child("Poblacion_enclave").setValue(poblacion_val);
                        nuevoEnclave.child("Provincia_enclave").setValue(provincia_val);

                        nuevoEnclave.child("Tipo_enclave").setValue("Sin definir");
                        nuevoEnclave.child("Latitud_enclave").setValue("Sin definir");
                        nuevoEnclave.child("Longitud_enclave").setValue("Sin definir");
                        nuevoEnclave.child("Comunidad_enclave").setValue("Sin definir");
                        nuevoEnclave.child("Autor_enclave").setValue(usuario_val);
                        nuevoEnclave.child("Email_autor_enclave").setValue(email_val);




                        nuevoEnclave.child("Imagen_Enclave").setValue(downloadUrl.toString());



                        mProgress.dismiss();

                        Toast.makeText(getActivity(),"Enclave enviado con éxito!", Toast.LENGTH_SHORT).show();
                        mNombreTxt.setText("");
                        mDescripcionTxt.setText("");
                        mSelectImage.setImageResource(R.drawable.imagen);

                    }
                });
            }

        else {
                Toast.makeText(getActivity(),"Faltan datos!", Toast.LENGTH_SHORT).show();
                if (mImageUri ==null){
                    Toast.makeText(getActivity(),"Falta una imagen del enclave!", Toast.LENGTH_SHORT).show();
                }
            }



    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_enclave_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }



}