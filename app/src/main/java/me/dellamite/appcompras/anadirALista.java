package me.dellamite.appcompras;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class anadirALista extends AppCompatActivity {
    public static  final String KEY_NOMBRE_LISTA = "key_nombre_lista";
    public static final String KEY_EMAIL = "key_email";
    private ListView lvVista;
    private Button bCrearLista;
    private ArrayList<String> alListas;
    private ArrayAdapter<String> aaAdapter;
    private String email, nombreD;
    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_a_lista);

        Intent intent = getIntent();
        nombreD = intent.getStringExtra(VistaActivity.KEY_NOMBRE);
        email = intent.getStringExtra(VistaActivity.KEY_EMAIL);


        lvVista = findViewById(R.id.lvLista);
        bCrearLista = findViewById(R.id.bCrearLista);

        alListas = new ArrayList<String>();
        llenarlista();
        aaAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,alListas);

        lvVista.setAdapter(aaAdapter);

        bCrearLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEnviar = new Intent(getApplicationContext(),CrearListaActivity.class);
                intentEnviar.putExtra(KEY_EMAIL,email);
                startActivity(intentEnviar);
            }
        });

        lvVista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jalarDatos(email);
            }
        });
    }

    private void jalarDatos(String email) {
        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("listas");

        dbReference.orderByChild("emailAutor").equalTo(email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                guardarProducto(snapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void guardarProducto(DataSnapshot snapshot) {
        Lista lista = snapshot.getValue(Lista.class);
        String emailLista = lista.getEmailAutor();
        ArrayList<String> Productos = new ArrayList<String>();


        if (email.equals(emailLista)){
            Lista lista1 = new Lista();
            Productos=lista.getProducto();
            if (Productos.isEmpty()==false){
                Productos.add(nombreD);
            }else {
                Productos.add(nombreD);
            }
            dbReference = db.getReference("listas/"+snapshot.getKey());
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("producto", Productos);
            dbReference.updateChildren(childUpdates);
            Toast.makeText(getApplicationContext(),"Se ha guardado el producto",Toast.LENGTH_SHORT).show();
        }
    }

    private void llenarlista() {
        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("listas");

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                jalaLista(snapshot,email);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void jalaLista(DataSnapshot snapshot, String email) {
        Lista lista = snapshot.getValue(Lista.class);
        String emailLista = lista.getEmailAutor();

        if (email.equals(emailLista)){
            String listaNombre = lista.getNombreLista();
            aaAdapter.add(listaNombre);
        }
    }


}