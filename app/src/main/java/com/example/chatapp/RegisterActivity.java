package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText RegisterEmail,RegisterPassword;
    private Button CreateAccount;
    private TextView AlreadyhaveAccount;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterEmail = findViewById(R.id.register_email);
        RegisterPassword = findViewById(R.id.register_password);
        loadingBar = new ProgressDialog(this);

        CreateAccount = findViewById(R.id.register_button);
        AlreadyhaveAccount = findViewById(R.id.already_have_account_link);

        AlreadyhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {

        String Email = RegisterEmail.getText().toString();
        String Password = RegisterPassword.getText().toString();

        if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password))
        {
            Toast.makeText(this, "Please , insert the email and password", Toast.LENGTH_SHORT).show();

        }
        else{
            loadingBar.setTitle("Creating new account");
            loadingBar.setMessage("Please wait , while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete( Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                    else{
                        String message = task.getException().toString();
                        Toast.makeText(RegisterActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }


                }
            });

        }
    }


}