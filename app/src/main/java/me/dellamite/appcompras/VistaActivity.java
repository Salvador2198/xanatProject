package me.dellamite.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VistaActivity extends AppCompatActivity {
    public static final String KEY_NOMBRE = "key_nombre";
    public static final String KEY_EMAIL = "key_email";
    private TextView tvNombreProductoD, tvCantidadProducto, tvPrecio1, tvPrecio2, tvPrecio3;
    private ImageView ivProductoDetalle;
    private Button bLista;

    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_producto);

        tvNombreProductoD = findViewById(R.id.tvNombreProductoD);
        tvCantidadProducto = findViewById(R.id.tvCantidadProducto);
        ivProductoDetalle = findViewById(R.id.ivProductoDetalle);
        tvPrecio1 = findViewById(R.id.tvPrecio1);
        tvPrecio2 = findViewById(R.id.tvPrecio2);
        tvPrecio3 = findViewById(R.id.tvPrecio3);
        bLista = findViewById(R.id.bLista);

        Intent intent = getIntent();
        String NombreD = intent.getStringExtra(ProductosActivity.KEY_NOMBRE);
        String email = intent.getStringExtra(ProductosActivity.KEY_EMAIL);

        tvNombreProductoD.setText(NombreD);

        bLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEnviar = new Intent(VistaActivity.this,anadirALista.class);
                intentEnviar.putExtra(KEY_NOMBRE,NombreD);
                intentEnviar.putExtra(KEY_EMAIL,email);
                startActivity(intentEnviar);
            }
        });

        db = FirebaseDatabase.getInstance("https://dellamite-app-compras-default-rtdb.firebaseio.com/");
        dbReference = db.getReference("productos");

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Producto producto = snapshot.getValue(Producto.class);

                String NombrePD = producto.getNombre();
                if (NombreD.equals(NombrePD)){
                    Float cantidad = producto.getCantidad();
                    String unidad = producto.getUnidad();
                    String cantidadS = cantidad.toString();
                    tvCantidadProducto.setText(cantidadS+unidad);
                    Float precio1 = producto.getPrecio1();
                    Float precio2 = producto.getPrecio2();
                    Float precio3 = producto.getPrecio3();
                    String precio1S = precio1.toString();
                    String precio2S = precio2.toString();
                    String precio3S = precio3.toString();
                    tvPrecio1.setText(precio1S);
                    tvPrecio2.setText(precio2S);
                    tvPrecio3.setText(precio3S);
                    String urlImage = producto.getImagen();
                    Glide.with(getApplicationContext()).load(urlImage).into(ivProductoDetalle);
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
