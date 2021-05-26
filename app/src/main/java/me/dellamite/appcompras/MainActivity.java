package me.dellamite.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static me.dellamite.appcompras.AuthActivity.KEY_EMAIL;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_CATEGORIA = "key_categoria";
    public static final String KEY_EMAIL = "key_email";
    private ImageView ivFrutasVerduras, ivCarnesMariscos, ivHogar, ivVestimenta, ivVinosLicores, ivBebidas, ivElectronica, ivLacteoHuevos, ivJugetesDeportes, ivAbarrotes, ivHigieneSaludBelleza, ivMascotas, ivPanaderia, ivLimpiezaPapeleria, ivMenu;
    private TextView tvFrutasVerduras, tvCarnesMariscos, tvHogar, tvVestimenta, tvVinosLicores, tvBebidas, tvElectronica, tvLacteoHuevos, tvJugetesDeportes, tvAbarrotes, tvHigieneSaludBelleza, tvMascotas, tvPanaderia, tvLimpiezaPapeleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String email = intent.getStringExtra(KEY_EMAIL);

        ivMenu = findViewById(R.id.ivMenu);

        ivFrutasVerduras = findViewById(R.id.ivFrutasVerduras);
        tvFrutasVerduras = findViewById(R.id.tvFrutasVerduras);

        ivCarnesMariscos = findViewById(R.id.ivCarnesMariscos);
        tvCarnesMariscos = findViewById(R.id.tvCarnesMariscos);

        ivHogar = findViewById(R.id.ivHogar);
        tvHogar = findViewById(R.id.tvHogar);

        ivVestimenta = findViewById(R.id.ivVestimenta);
        tvVestimenta = findViewById(R.id.tvVestimenta);

        ivVinosLicores = findViewById(R.id.ivVinosLicores);
        tvVinosLicores = findViewById(R.id.tvVinosLicores);

        ivBebidas = findViewById(R.id.ivBebidas);
        tvBebidas = findViewById(R.id.tvBebidas);

        ivElectronica = findViewById(R.id.ivElectronica);
        tvElectronica = findViewById(R.id.tvElectronica);

        ivLacteoHuevos = findViewById(R.id.ivLacteoHuevos);
        tvLacteoHuevos = findViewById(R.id.tvLacteoHuevos);

        ivJugetesDeportes = findViewById(R.id.ivJugetesDeportes);
        tvJugetesDeportes = findViewById(R.id.tvJugetesDeportes);

        ivAbarrotes = findViewById(R.id.ivAbarrotes);
        tvAbarrotes = findViewById(R.id.tvAbarrotes);

        ivHigieneSaludBelleza = findViewById(R.id.ivHigieneSaludBelleza);
        tvHigieneSaludBelleza = findViewById(R.id.tvHigieneSaludBelleza);

        ivMascotas = findViewById(R.id.ivMascotas);
        tvMascotas = findViewById(R.id.tvMascotas);

        ivPanaderia = findViewById(R.id.ivPanaderia);
        tvPanaderia = findViewById(R.id.tvPanaderia);

        ivLimpiezaPapeleria = findViewById(R.id.ivLimpiezaPapeleria);
        tvLimpiezaPapeleria = findViewById(R.id.tvLimpiezaPapeleria);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,menuActivity.class);
                intent.putExtra(KEY_EMAIL,email);
                startActivity(intent);
            }
        });

        ivFrutasVerduras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvFrutasVerduras.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivCarnesMariscos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvCarnesMariscos.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivHogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvHogar.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivVestimenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvVestimenta.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivVinosLicores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvVinosLicores.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvBebidas.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivElectronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvElectronica.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivLacteoHuevos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvLacteoHuevos.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivJugetesDeportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvJugetesDeportes.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivAbarrotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvAbarrotes.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivHigieneSaludBelleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvHigieneSaludBelleza.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvMascotas.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivPanaderia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvPanaderia.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });

        ivLimpiezaPapeleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                String categoria = tvLimpiezaPapeleria.getText().toString();
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_CATEGORIA,categoria);
                startActivity(intent);
            }
        });
    }

}