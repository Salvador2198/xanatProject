package me.dellamite.appcompras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class menuActivity extends AppCompatActivity {
    public static final String KEY_EMAIL = "key_email";
    private String email;
    private Button btnRegistro, bCerrarSesion;
    private ImageButton ibListas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.KEY_EMAIL);

        ibListas = findViewById(R.id.ibListas);
        ibListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamaListas();
            }
        });

        bCerrarSesion = findViewById(R.id.bCerrarSesion);
        bCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
                LoginManager.getInstance().logOut();
                Context context = getApplicationContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences(
                        getString(R.string.prefs_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent1 = new Intent(menuActivity.this,AuthActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void llamaListas() {
        Intent intentListas = new Intent(menuActivity.this, listasActivity.class);
        intentListas.putExtra(KEY_EMAIL,email);
        startActivity(intentListas);
    }
}