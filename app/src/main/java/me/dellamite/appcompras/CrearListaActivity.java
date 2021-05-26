package me.dellamite.appcompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static me.dellamite.appcompras.anadirALista.KEY_NOMBRE_LISTA;

public class CrearListaActivity extends AppCompatActivity {
    private String email;
    private EditText etNombreLista;
    private Button bGuardarLista;
    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_lista);

        Intent intent = getIntent();
        email = intent.getStringExtra(anadirALista.KEY_EMAIL);

        etNombreLista = findViewById(R.id.etNombreLista);
        bGuardarLista = findViewById(R.id.bGuardarLista);

        bGuardarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLista(email);
            }
        });
    }

    private void guardarLista(String email) {
        String nombreLista = etNombreLista.getText().toString();

        if (nombreLista.equals("")==false){
            db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
            dbReference = db.getReference("listas");
            /*
            ArrayList<String> vacio = new ArrayList<String>();
            vacio.add("");
            lista.setProducto(vacio);

             */


            Lista lista = new Lista();
            lista.setNombreLista(nombreLista);
            lista.setEmailAutor(email);


            dbReference.push().setValue(lista);
            finish();
        }else{
            String pocoTexto = "Debe colocar el nombre de la lista";
            Toast.makeText(getApplicationContext(),pocoTexto,Toast.LENGTH_SHORT).show();
        }

    }
}