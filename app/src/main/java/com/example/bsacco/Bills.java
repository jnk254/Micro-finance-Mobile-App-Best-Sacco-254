package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bills extends AppCompatActivity {

    private static final String TAG = "bills";
    private static final String KEY_BILLTYPE = "Bill Type";
    private static final String KEY_BILLAMOUNT= "Bill Amount";


    private EditText billtype;
    private EditText billamount;
    private Button billbtn;
    private Button billviewbtn;
    private String user_id;
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        billtype = findViewById(R.id.billtype);
        billamount = findViewById(R.id.billamount);
        billbtn = findViewById(R.id.btnbill);
        billviewbtn = findViewById(R.id.btnbillview);

        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bill_type= billtype.getText().toString();
                String bill_amount = billamount.getText().toString();

                if(TextUtils.isEmpty(bill_type)){
                   billtype.setError("Enter Bill Type");
                   return;
               }
               if(TextUtils.isEmpty(bill_amount)){
                   billamount.setError("Enter Amount");
                   return;
               }

                Map<String, Object> bill = new HashMap<String, Object>();
                bill.put(KEY_BILLTYPE, bill_type);
                bill.put(KEY_BILLAMOUNT, bill_amount);
                String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                db.collection("Bills").document().set(bill)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Bills.this,"Paid", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Bills.this,"Error", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
        billviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bill_type= billtype.getText().toString();
                String bill_amount = billamount.getText().toString();

                //read to account details
                DocumentReference billdetails = db.collection("Bills").document();
                billdetails.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            Intent fields = new Intent();
                            fields.setClass(Bills.this, Accdetails.class);
                            fields.putExtra("Bill type",bill_type);
                            fields.putExtra("Bill amount",bill_amount);
                            startActivity(fields);

                        }
                    }

                });
            }
        });


    }
}
