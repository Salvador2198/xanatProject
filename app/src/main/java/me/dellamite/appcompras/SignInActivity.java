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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private EditText etEmailRegistrar, etContraseñaRegistrar, etContraseñaComprueba;
    private Button btnRegistrar;
    private TextView tvTitle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmailRegistrar = findViewById(R.id.etEmailRecovery);
        etContraseñaRegistrar = findViewById(R.id.etContrasenaAuth);
        etContraseñaComprueba = findViewById(R.id.etContraseñaComp);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        mAuth = FirebaseAuth.getInstance();

        setup();
    }


    private void setup() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailRegistrar.getText().toString().trim();
                String contraseña = etContraseñaRegistrar.getText().toString().trim();
                String contraseñaComp = etContraseñaComprueba.getText().toString().trim();
                if (!email.isEmpty() && !contraseña.isEmpty() && (contraseña.equals(contraseñaComp))){
                    mAuth.createUserWithEmailAndPassword(email, contraseña)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent recoveryIntent = new Intent();
                                        setResult(RESULT_OK, recoveryIntent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "No se ha podido realizar el registro",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}