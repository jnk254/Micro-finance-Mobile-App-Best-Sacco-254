package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Transaction extends AppCompatActivity {
    private static final String KEY_ENTER_TRANSACTION = "Transcation_amount";

    CheckBox deposit, withdrawal;
    private EditText transactionamount;
    Button transaction;
    Transact trans;
    private TextView depamount;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Firestore Object
    private double depositedamount;
    private double withdrawamount;
    private double transamountt;
    private double NewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        addListenerOnButtonClick();
    }

    private void addListenerOnButtonClick() {
        deposit = findViewById(R.id.depositcheck);
        withdrawal = findViewById(R.id.withdrawalcheck);
        transactionamount = findViewById(R.id.transactionamount);
        transaction = findViewById(R.id.btntransact);
        depamount =findViewById(R.id.displaydepositedamount);

        trans = new Transact();

        String dep = "Deposit";
        String with = "Withdrawal";
        String transamount = transactionamount.getText().toString();

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.depositcheck:
                        if (deposit.isChecked()) {
                            db.collection("Transaction").document();
                            depositedamount = Double.parseDouble(depamount.getText().toString());
                            transamountt= Double.parseDouble(transactionamount.getText().toString());

                            Deposits dp = new Deposits();
                            dp.setBalance(depositedamount);
                            dp.setDeposit(transamountt);

                            //calculate new totalsavings
                            NewBalance = dp.getNewBalance();

                            depositedamount = NewBalance;

                            transamountt = 0;

                        }
                        break;
                    case R.id.withdrawalcheck:
                        if (withdrawal.isChecked()) {
                            db.collection("Transaction").document();
                            withdrawamount = Double.parseDouble(depamount.getText().toString());
                            transamountt= Double.parseDouble(transactionamount.getText().toString());
                             Withdraws wd =new Withdraws();

                             wd.setBalance(withdrawamount);
                             wd.setWithdraw(transamountt);

                            NewBalance = wd.getNewBalance();
                            withdrawamount = NewBalance;
                            transamountt = 0;
                        }
                        break;

                }
                Map<String, Object> cash = new HashMap<String, Object>();
                cash.put(KEY_ENTER_TRANSACTION, transamount);
                db.collection("Transaction").document().set(cash)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Transaction.this, "Successful transaction", Toast.LENGTH_SHORT).show();
                            }

                        });

            }
        });
    }
}

          /*deposit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  depositedamount = Double.parseDouble(depamount.getText().toString());
                  transamountt= Double.parseDouble(transactionamount.getText().toString());

                  Deposits dp = new Deposits();
                  dp.setBalance(depositedamount);
                  dp.setDeposit(transamountt);

                  //calculate new totalsavings
                  NewBalance = dp.getNewBalance();

                  depositedamount = NewBalance;

                  transamountt = 0;
              }
          });*/





//}
//}