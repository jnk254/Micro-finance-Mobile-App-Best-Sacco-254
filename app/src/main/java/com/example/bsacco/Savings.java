package com.example.bsacco;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class Savings extends AppCompatActivity {

    private static final String TAG = "savings";
    private static final String KEY_SAVING_AMOUNT = "Saving_amount";
    private static final String KEY_TOTAL = "Total_Saving_amount";


    private String user_id;
    private Button btndeposits;
    private Button btnwithdrawal;
    private EditText savingamount;
    private TextView totalsavings; // only this class will use it

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object

    public double Total_savings;
    public double DepositEntered;
    public double NewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        savingamount = findViewById(R.id.saveamount);
        totalsavings = findViewById(R.id.displaysavingtotal);
        btndeposits = findViewById(R.id.savedeposit);
        btnwithdrawal = findViewById(R.id.savewithdrawal);



        btndeposits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*savingCalculations();
                deposit();
                getBalance();
                savingDb();*/

                if (!TextUtils.isEmpty(savingamount.getText())) {
                    DepositEntered = Double.parseDouble(savingamount.getText().toString());
                    Total_savings = Double.parseDouble(totalsavings.getText().toString());
                    //create deposit object
                    Deposits dp = new Deposits();
                    dp.setBalance(Total_savings);
                    dp.setDeposit(DepositEntered);

                    //calculate new totalsavings
                    NewBalance = dp.getNewBalance();

                    Total_savings = NewBalance;

                    DepositEntered = 0;
                }//end if
                //deposit filed is empty, prompt user to enter deposit amount
                else {
                    Toast.makeText(Savings.this, "Please enter deposit amount and try again!", Toast.LENGTH_SHORT).show();
                }
                //clear Deposit field

                savingamount.setText(null);
            }
        });

        btnwithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*savingCalculations();
                withdraw();
                getBalance();
                savingDb();*/
            }
        });
    }

}
          /*  //set totalsavings to 0.0
            private void savingCalculations(){
                double Total_savings = Double.parseDouble(totalsavings.getText().toString());
                 Total_savings = 0.0;
            }

    //Deposit
    public void deposit() {
        double Total_savings = Double.parseDouble(totalsavings.getText().toString());
        double Saving_amount = Double.parseDouble(savingamount.getText().toString());
        Total_savings = Total_savings + Saving_amount;
    }
    //withdraw
    public double withdraw() {
        double Total_savings = Double.parseDouble(totalsavings.getText().toString());
        double Saving_amount = Double.parseDouble(savingamount.getText().toString());
        if (Total_savings>=Saving_amount)
            Total_savings = Total_savings- Saving_amount;
        else
            Toast.makeText(Savings.this, "Saved", Toast.LENGTH_SHORT).show();
        return Total_savings;

    }
    //get balance
    public double getBalance() {

        double Total_savings = Double.parseDouble(totalsavings.getText().toString());
        return Total_savings;
    }



    // to firestore db
    private void savingDb(){


        double Saving_amount = Double.parseDouble(savingamount.getText().toString());
        double Total_savings = Double.parseDouble(totalsavings.getText().toString());
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> saves = new HashMap<String, Object>();
        saves.put(KEY_SAVING_AMOUNT, Saving_amount);
        saves.put(KEY_TOTAL,Total_savings);


        db.collection("Savings").document().set(saves)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Savings.this, "Saved", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    //display
    private void readInvest() {

        DocumentReference savingdetails= db.collection("Savings").document();
        savingdetails.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    ///fields.append("Saving Amount: ").append(doc.get("Saving_amount"));
                    fields.append("Total Saving Amount: ").append(doc.get("Total_saving_amount"));
                    ///fields.append("\n Investment Period(month): ").append(doc.get("investment_period"));
                    totalsavings.setText(fields.toString());
                }
            }
        });
    }

*/
