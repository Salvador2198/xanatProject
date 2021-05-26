package me.dellamite.appcompras;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity {
    private TextView tvCorreoHome, tvContraseñaHome, tvTitle;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvCorreoHome = findViewById(R.id.tvCorreoHome);
        tvContraseñaHome = findViewById(R.id.tvContrasenaHome);

        Intent homeIntent = getIntent();
        tvCorreoHome.setText(homeIntent.getStringExtra(AuthActivity.KEY_EMAIL));
        tvContraseñaHome.setText(homeIntent.getStringExtra(AuthActivity.KEY_USER));

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("email", homeIntent.getStringExtra(AuthActivity.KEY_EMAIL));
        editor.putString("user", homeIntent.getStringExtra(AuthActivity.KEY_USER));
        editor.commit();

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
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
            }
        });
    }

}