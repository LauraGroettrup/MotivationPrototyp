package com.joanneum.motivation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.registration);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkInput();
            }
        });
    }

    private void checkInput() {
        String userName;
        String password = null;
        String email = null;
        TextView errorView = findViewById(R.id.lblRegistrationError);
        String errorMsg = "Something went wrong: " + errorView.getText().toString();
        TextView view;
        view = findViewById(R.id.editEnterUsername);
        userName = view.getText().toString();
        if (userName.equals("")) {
            errorView.setText(errorMsg + "Username must not be empty");
            errorView.setVisibility(View.VISIBLE);
            userName = null;
        }
        view = findViewById(R.id.editEnterEmail);
        email = view.getText().toString();
        if (email.equals("")) {
            errorView.setText(errorMsg + "Email must not be empty");
            errorView.setVisibility(View.VISIBLE);
            email = null;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorView.setText(errorMsg + "Not a valid email");
            errorView.setVisibility(View.VISIBLE);
            email = null;
        }
        view = findViewById(R.id.editEnterPasswort);
        TextView view2 = findViewById(R.id.editRepeatPasswort);
        password = view.getText().toString();
        if (password.equals("")) {
            errorView.setText(errorMsg + "Password must not be empty");
            errorView.setVisibility(View.VISIBLE);
            password = null;
        } else if (!view.getText().toString().equals(view2.getText().toString())) {
            errorView.setText(errorMsg + "Differnt passwords");
            errorView.setVisibility(View.VISIBLE);
            email = null;
        }
        if (!(email == null || userName == null || password == null)) {
            createAcc(password, email, userName);
        }
    }

    private void createAcc(String password, String email, final String userName) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("username", userName + "#" + user.getUid().hashCode());
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
