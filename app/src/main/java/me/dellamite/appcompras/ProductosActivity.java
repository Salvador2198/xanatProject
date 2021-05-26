package me.dellamite.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    public static final String KEY_NOMBRE = "key_nombre";
    public static final String KEY_EMAIL = "key_email";
    public String email;
    private RecyclerView rvProductos;
    private ProductosAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<String> nombresProducs = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        llenarLista();

        rvProductos = findViewById(R.id.rvProductPrecio);
        adapter = new ProductosAdapter(this);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductosActivity.this,VistaActivity.class);
                String NombreD = nombresProducs.get(rvProductos.getChildAdapterPosition(v));
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_NOMBRE,NombreD);
                startActivity(intent);
            }
        });

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);

        rvProductos.setAdapter(adapter);
        rvProductos.setLayoutManager(gridLayoutManager);


    }

    private void llenarLista() {

        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("productos");

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Intent intent = getIntent();
                if (intent!=null){
                    filtrarAdapter(intent,snapshot);
                }

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

    private void filtrarAdapter(Intent intent, DataSnapshot snapshot) {
        Producto producto = snapshot.getValue(Producto.class);

        String categoProduc = intent.getStringExtra(MainActivity.KEY_CATEGORIA);
        email = intent.getStringExtra(MainActivity.KEY_EMAIL);
        String categoria = producto.getCategoria();
        if (categoria.equals(categoProduc)){
            nombresProducs.add(producto.getNombre());
            adapter.add(producto);
        }
    }




}