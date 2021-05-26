package me.dellamite.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listasActivity extends AppCompatActivity {
    public static final String KEY_EMAIL = "key_email";
    private String email;
    private ListView lvListas;
    private ArrayList<String> alListas;
    private ArrayAdapter<String> aaAdapter;
    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        Intent intent = getIntent();
        email = intent.getStringExtra(menuActivity.KEY_EMAIL);

        lvListas = findViewById(R.id.lvListas);

        alListas = new ArrayList<String>();
        llenarlista();
        aaAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,alListas);

        lvListas.setAdapter(aaAdapter);

        lvListas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentEnviar = new Intent(listasActivity.this,productosDeListaActivity.class);
                intentEnviar.putExtra(KEY_EMAIL,email);
                startActivity(intentEnviar);
            }
        });
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

    private void regresaMenuL() {
        Intent intentRegresaMenuL = new Intent(listasActivity.this, menuActivity.class);
        startActivity(intentRegresaMenuL);
    }
}