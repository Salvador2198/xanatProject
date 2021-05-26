package me.dellamite.appcompras;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class AuthActivity extends AppCompatActivity {
    public static final String KEY_USER = "key_user";
    public static final String KEY_EMAIL = "key_email";
    private static final int KEY_GOOGLE_SIGN = 1;
    private static final int KEY_RECOVERY = 2;
    private static final int KEY_SIGN_IN = 3;
    private TextView tvTitle;
    private EditText etEmailAuth, etContraseñaAuth;
    private Button btnSignGoogle, btnSignFacebook, btnIngresar, btnRegistrar, btnRecuperar;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //Configuración de barra de titulo

        //Configurar instancia de usuario
        mAuth = FirebaseAuth.getInstance();

        //Declaración de variables botones
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSignGoogle = findViewById(R.id.btnSignGoogle);
        btnSignFacebook = findViewById(R.id.btnSignFacebook);
        btnRecuperar = findViewById(R.id.btnRecuperar);

        //Declaracion de variables editText
        etEmailAuth = findViewById(R.id.etEmailRecovery);
        etContraseñaAuth = findViewById(R.id.etContrasenaAuth);

        //Configuración de espera de respuesta login
        setup();

        //Configuración si ya hay datos guardados en la app
        sesion();
    }



    private void sesion() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email",  null);
        String user = sharedPreferences.getString("user",  null);

        if(email != null && user != null) {
            ingresarHome(email, user);
        }
    }

    private void setup() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AuthActivity.this, SignInActivity.class);
                startActivityForResult(homeIntent, KEY_SIGN_IN);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailAuth.getText().toString();
                String contraseña = etContraseñaAuth.getText().toString();
                if (!email.isEmpty() && !contraseña.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, contraseña)
                            .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String name = user.getDisplayName();
                                        String email = user.getEmail();
                                        String provider = user.getProviderId();
                                        ingresarHome(email, provider);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        showAlert();
                                    }
                                }
                            });
                }
            }
        });

        btnSignGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Configure Google Sign In
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(AuthActivity.this, gso);
                mGoogleSignInClient.signOut();

                Intent signIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signIntent, KEY_GOOGLE_SIGN);
            }
        });

        btnSignFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(AuthActivity.this, Arrays.asList("email"));
                callbackManager = CallbackManager.Factory.create();
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken token = loginResult.getAccessToken();
                        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                        mAuth.signInWithCredential(credential)
                                .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.i("MainActivity", "Exito al ingresar ");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            String name = user.getDisplayName();
                                            String email = user.getEmail();
                                            ingresarHome(name, email);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.i("MainActivity", "Hubo un fallo: ", task.getException());
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {
                        showAlert();
                    }
                });
            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AuthActivity.this, RecoveryActivity.class);
                startActivityForResult(homeIntent, KEY_RECOVERY);
            }
        });


    }

    private void showAlert() {
        //Crear el objeto builder
        AlertDialog.Builder alert = new AlertDialog.Builder(AuthActivity.this);
        //Configurar el diálogo
        alert.setTitle(getString(R.string.fallo_login))
                .setMessage(getString(R.string.fallo_login_desc))
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Acción en caso de clic en Aceptar

                    }
                }).show();
    }

    private void ingresarHome(String email, String user) {
        Intent homeIntent = new Intent(AuthActivity.this, MainActivity.class);
        homeIntent.putExtra(KEY_EMAIL, email);
        homeIntent.putExtra(KEY_USER, user);
        startActivity(homeIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == KEY_GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    mAuth.signInWithCredential(credential)
                            .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String name = user.getDisplayName();
                                        String email = user.getEmail();
                                        ingresarHome(email, name);
                                    } else {
                                        showAlert();
                                    }
                                }
                            });
                }
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                showAlert();
            }
        }
        if (requestCode == KEY_RECOVERY && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Se ha enviado un correo de recuperación de contraseña",
                    Toast.LENGTH_SHORT).show();
        }
        if(requestCode == KEY_SIGN_IN && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Se ha registrado el usuario",
                    Toast.LENGTH_SHORT).show();
        }
    }
}