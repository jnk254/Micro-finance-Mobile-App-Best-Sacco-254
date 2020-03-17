package com.example.bsacco;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName, mEmail, mPassword, mConfirm;
    private Button mButton;
    private CircleImageView mImage;
    private TextView mHaveAnAccount;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializer();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAccount()) {
                    doRegister();
                }

            }
        });
        mHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void doRegister() {

        mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Njau", task.getException().toString());

                }
            }
        });
    }

    public void initializer() {

        mName = findViewById(R.id.userName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.passWord);
        mConfirm = findViewById(R.id.confirmPassword);
        mButton = findViewById(R.id.btn_register);
        mImage = findViewById(R.id.reg_account);
        mHaveAnAccount = findViewById(R.id.txt_already_have_an_account);
        mAuth = FirebaseAuth.getInstance();

    }

    private boolean validateAccount() {
        boolean isInputValid = false;

        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirm.getText().toString().trim();

        if (!name.isEmpty()) {
            if (!email.isEmpty()) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        if (!confirmPassword.isEmpty()) {
                            if (password.equals(confirmPassword)) {
                                if (password.length() >= 6) {
                                    isInputValid = true;
                                } else {
                                    mPassword.setError("Password Should Be 6 Characters Long");
                                    mPassword.requestFocus();
                                }
                            } else {
                                Toast.makeText(this, "Password Does Not Match", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            mConfirm.setError("Confirmation Password is required");
                            mConfirm.requestFocus();
                        }
                    } else {
                        mPassword.setError("Password is required");
                        mPassword.requestFocus();
                    }
                } else {
                    mEmail.setError("email is required");
                    mEmail.requestFocus();
                }
            } else {
                mEmail.setError("Email is required");
                mEmail.requestFocus();
            }
        } else {
            mName.setError("Name is required");
            mName.requestFocus();
        }
        return isInputValid;
    }


}
