package com.example.bsacco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmail,mPassword,mCpassword;
    Button mButton;
    TextView mText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initVariables();

        verify();

    }

    private void verify() {

        String email= mEmail.getText().toString().trim();
        String password =mPassword.getText().toString().trim();
        String cPassword = mCpassword.getText().toString().trim();

        if(!email.isEmpty()){
            if(email.matches(String email));

        }else{
            mEmail.setError("Email required");
            mEmail.requestFocus();
        }
    }

    private void initVariables() {
        mEmail.findViewById(R.id.register_email);
        mPassword.findViewById(R.id.register_password);
        mCpassword.findViewById(R.id.register_cPassword);
        mButton.findViewById(R.id.register_btn);
        mText.findViewById(R.id.register_txt);
    }

}
