package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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

public class Investment extends AppCompatActivity {
    private static final String TAG = "investment";
    private static final String KEY_INVEST = "investment_amount";
    private static final String KEY_INVEST_TYPE = "invest_type";
    private static final String KEY_INVESTPERIOD = "invest_period";


    private EditText investmentamount;
    private EditText investtype;
    private EditText investperiod;
    private TextView investdetail;
    private Button investbtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);


        investdetail =  findViewById(R.id.displayinvest);
        investmentamount = findViewById(R.id.investamount);
        investtype = findViewById(R.id.investname);
        investperiod = findViewById(R.id.investperiod);
        investbtn = findViewById(R.id.btninvest);

        investbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readInvest();

                String Investment_amount = investmentamount.getText().toString();
                String Investment_Type = investtype.getText().toString();
                String Investment_Period = investperiod.getText().toString();

                Map<String, Object> invest = new HashMap<String, Object>();
                invest.put(KEY_INVEST, Investment_amount);
                invest.put(KEY_INVEST_TYPE, Investment_Type);
                invest.put(KEY_INVESTPERIOD, Investment_Period);

                db.collection("Investment").document().set(invest)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Investment.this, "added Investment", Toast.LENGTH_SHORT).show();
                            }
                        });
                //if deposit field is not empty, get deposit amount
               /* if (!Investment_amount.isEmpty()) {
                    investmentamount = Double.parseDouble(String.valueOf(investmentamount.getText()));
                } else {
                    investmentamount.setError("Investment Amount required");
                    investmentamount.requestFocus();

                    Deposits inv = new Deposits();
                    inv.setBalance();
                    inv.setDeposit(investmentamount);

                }*/


            }
        });

    }
        private void readInvest() {

            DocumentReference investdetails = db.collection("Investment").document();
            investdetails.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        StringBuilder fields = new StringBuilder("");
                        fields.append("Investment Amount: ").append(doc.get("investment_amount"));
                        fields.append("\nInvestment Type: ").append(doc.get("investment_type"));
                        fields.append("\n Investment Period(month): ").append(doc.get("investment_period"));
                        investdetail.setText(fields.toString());
                    }
                }
            });
        }
    }
