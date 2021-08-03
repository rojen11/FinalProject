package com.rojen.canteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginText = findViewById(R.id.loginText);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // sign in
        TextInputEditText email = findViewById(R.id.emailField);
        TextInputEditText password = findViewById(R.id.passwordField);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    email.setError("Enter a valid email address.");
                    email.requestFocus();
                    return;
                }

                if (passwordText.length() < 8) {
                    password.setError("Enter at least 8 characters");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Successfully registered.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}