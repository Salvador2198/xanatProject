package me.dellamite.appcompras;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class productosDeListaActivity extends AppCompatActivity {
    private TextView tvPrecioWF, tvPrecioCF, tvPrecioSF;
    private RecyclerView rvProductPrecio;
    private String email;
    private ArrayList<Lista> datosL;
    private int i = 0;
    private Float PrecioC = 0.0f, PrecioW = 0.0f, PrecioS = 0.0f, PrecioCF = 0.0f, PrecioWF = 0.0f, PrecioSF = 0.0f;

    private ListadoAdapter listadoAdapter;

    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_de_lista);

        Intent intent = getIntent();
        email = intent.getStringExtra(listasActivity.KEY_EMAIL);

        datosL = new ArrayList<Lista>();
        llenarProductoListado();

        rvProductPrecio = findViewById(R.id.rvProductPrecio);

        tvPrecioCF = findViewById(R.id.tvPrecioFinalC);
        tvPrecioSF = findViewById(R.id.tvPrecioFinalS);
        tvPrecioWF = findViewById(R.id.tvPrecioFinalW);

        listadoAdapter = new ListadoAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProductPrecio.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        rvProductPrecio.setAdapter(listadoAdapter);
        rvProductPrecio.addItemDecoration(dividerItemDecoration);

    }

    private void llenarProductoListado() {
        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("listas");

        dbReference.orderByChild("emailAutor").equalTo(email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mostrarProducto(snapshot);
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

    private void mostrarProducto(DataSnapshot snapshot) {
        Lista lista = snapshot.getValue(Lista.class);

        ArrayList<String> product = lista.getProducto();

        for (int i=0;i<product.size();i++){
            String emailDB = lista.getEmailAutor();
            ArrayList<String> product2 = new ArrayList<String>();
            product2.add(product.get(i));
            Lista lista2 = new Lista();
            lista2.setProducto(product2);
            jalarPrecio(lista2,emailDB);
        }


    }

    private void jalarPrecio(Lista lista2, String emailDB) {
        Log.i("MainActivity", "Entre we");

        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("productos");

        dbReference.orderByChild("nombre").equalTo(String.valueOf(lista2.getProducto().get(0))).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Producto producto = snapshot.getValue(Producto.class);

                String NomPro = producto.getNombre();

                Log.i("MainActivity","Voa entrar we");

                PrecioC = producto.getPrecio1();
                PrecioW = producto.getPrecio2();
                PrecioS = producto.getPrecio3();

                PrecioCF += PrecioC;
                PrecioWF += PrecioW;
                PrecioSF += PrecioS;

                lista2.setPrecioC(PrecioC);
                lista2.setPrecioS(PrecioS);
                lista2.setPrecioW(PrecioW);

                if (email.equals(emailDB)){
                    listadoAdapter.add(lista2);
                    tvPrecioCF.setText(PrecioCF.toString());
                    tvPrecioWF.setText(PrecioWF.toString());
                    tvPrecioSF.setText(PrecioSF.toString());
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
}