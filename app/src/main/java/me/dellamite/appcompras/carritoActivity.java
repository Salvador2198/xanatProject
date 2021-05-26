package me.dellamite.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class carritoActivity extends AppCompatActivity {
    private ImageButton ibBackC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        ibBackC = findViewById(R.id.ibBackC);
        ibBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresaMenuC();
            }
        });
    }

    private void regresaMenuC() {
        Intent intentRegresaMenuC = new Intent(carritoActivity.this,menuActivity.class);
        startActivity(intentRegresaMenuC);
    }
}