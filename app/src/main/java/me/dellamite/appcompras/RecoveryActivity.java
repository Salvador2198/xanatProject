package me.dellamite.appcompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etEmailRecovery;
    private Button btnRecuperarContraseña;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        etEmailRecovery = findViewById(R.id.etEmailRecovery);
        btnRecuperarContraseña = findViewById(R.id.btnRecuperarContraseña);

        mAuth = FirebaseAuth.getInstance();

        setup();
    }

    private void setup() {
        btnRecuperarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailRecovery.getText().toString().trim();
                if(!email.equals("")) {
                    recupearContraseña(email);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un correo",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recupearContraseña(String email) {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent recoveryIntent = new Intent();
                    setResult(RESULT_OK, recoveryIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "No puedo ser enviado el correo de restablecimiento",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}