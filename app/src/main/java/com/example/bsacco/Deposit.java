package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deposit extends AppCompatActivity {
    private static final String TAG = "deposit";
    private static final String KEY_FROM_MPESA = "FROM MPESA";
    private static final String KEY_FROM_BANK = "FROM BANK";
    private static final String KEY_ENTER_DEPOSIT_AMOUNT = "DEPOSIT AMOUNT";


    private RadioButton frommpesaRadiobutton;
    private RadioButton frombankRadiobutton;
    private EditText enterdepamount;
    private Button btndeposit;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);


        // FireStore Storage for Provider
        frommpesaRadiobutton = findViewById(R.id.frommpesa);
        frombankRadiobutton = findViewById(R.id.frombank);
        enterdepamount = findViewById(R.id.enterdepositamount);
        btndeposit = findViewById(R.id.deposit);

        btndeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String From_Mpesa = frommpesaRadiobutton.getText().toString();
                String From_Bank = frombankRadiobutton.getText().toString();
                String Amount = enterdepamount.getText().toString();

                Map<String, Object> deposit = new HashMap<String, Object>();
                deposit.put(KEY_FROM_MPESA, From_Mpesa);
                deposit.put(KEY_FROM_BANK, From_Bank);
                deposit.put(KEY_ENTER_DEPOSIT_AMOUNT, Amount);


                db.collection("Deposit").document("first").set(deposit)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Deposit.this,"Deposited", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Deposit.this,"Error", Toast.LENGTH_SHORT).show();

                            }
                        });

            }

            ;


        });
    }
}