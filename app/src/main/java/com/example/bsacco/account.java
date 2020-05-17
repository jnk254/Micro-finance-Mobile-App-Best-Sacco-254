package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class account extends AppCompatActivity {

    private static final String KEY_ACCTYPE = "Account_type";
    private static final String KEY_ACCNO = "Account_No";


    private TextView displayaccount,displayaccbalance;
    private EditText accounttype;
    private Button accnumber,accdetails;
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        //displayAccount();


         accnumber = findViewById(R.id.btn_accNo);
         accdetails = findViewById(R.id.btn_accdetails);


         accdetails.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent details = new Intent(account.this, Accdetails.class );
                 startActivity(details);
                 finish();
                 addAcctype();

             }
         });



         accnumber.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 addaccNo();
             }
         });
    }

    /*public void displayAccount() {
        DocumentReference accountdetails = db.collection("Account").document();
        accountdetails.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                displayaccbalance = findViewById(R.id.displayaccbal);

                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Account type: ").append(doc.get("Account_type"));
                    fields.append("\nAccount NO: ").append(doc.get("investment_type"));
                    displayaccount.setText(fields.toString());
            }
        }
    });

}*/


    private void addAcctype(){
        accounttype = findViewById(R.id.accounttype);
        String acctpyee = accounttype.getText().toString().trim();
        if (!acctpyee.isEmpty()) {
        } else {
            accounttype.setError("Account Type is required");
            accounttype.requestFocus();
        }
        Map<String, Object> account = new HashMap<String, Object>();
        account.put(KEY_ACCTYPE,acctpyee);
        account.put(KEY_ACCNO,displayaccount);
        db.collection("Account").document().set(account)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }

    //autogenerate accountno
    private void addaccNo(){
        Random rand = new Random();
        float number = rand.nextFloat()*100;
        displayaccount = findViewById(R.id.displayacctype);
        String myString = String.valueOf(number);
        displayaccount.setText(myString);
        Toast.makeText(account.this, "Your Account Number is:"+myString, Toast.LENGTH_LONG).show();
    }






}
   /* class AccountnoGenerator {
        //generate account number
        private AtomicInteger count =new AtomicInteger(0001);
        private static com.example.bsacco.AccountnoGenerator generator= new com.example.bsacco.AccountnoGenerator();

        private  AccountnoGenerator(){ }
        public static com.example.bsacco.AccountnoGenerator getInstance(){
            return generator;
        }

        public int generate(){
            return  count.getAndIncrement();
        }
    }*/


