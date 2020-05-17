package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loan extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG = "Loan";
    private static final String KEY_loan_amount = "Loan_amount";
    private static final String KEY_SPINNER_VALUE = "Loan_type";
    private static final String KEY_loan_period = "Loan_Period";

//create instance

    private String user_id;
    private EditText loan_amount;
    private Spinner loan_type;
    private EditText loan_period;
    private @ServerTimestamp
    Date timestamp;
    private Button apply_loan;
    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        //initialize

        loan_amount = findViewById(R.id.loan_amount);
        loan_period = findViewById(R.id.loan_period);
        apply_loan = findViewById(R.id.apply_loan);


        // get Spinner reference
        Spinner loan_type = findViewById(R.id.loan_type);

        // Spinner click listener
        loan_type.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List loantype = new ArrayList();
        loantype.add("Personal Loan");
        loantype.add("School Loan");
        loantype.add("Farm Loan");
        loantype.add("Investment");

        // Creating array adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, loantype);

        // Drop down style will be listview with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // attaching data adapter to spinner
        loan_type.setAdapter(dataAdapter);

        apply_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Loan_amount = loan_amount.getText().toString();
                loan_type.getSelectedItem().toString().trim();
                String Loan_period = loan_period.getText().toString();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


                Map<String, Object> loan = new HashMap<String, Object>();

                loan.put(KEY_loan_amount, loan_amount);
                loan.put(KEY_SPINNER_VALUE, loan_type);
                loan.put(KEY_loan_period, loan_period);

                db.collection("Loan").document().set(loan)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        }); //


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


   /* @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        // getting selected item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item in toast
        Toast.makeText(parent.getContext(), "Selected Loan: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView arg0) {

    }*/








