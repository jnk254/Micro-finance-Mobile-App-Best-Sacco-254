package com.example.bsacco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        ListView listview = (ListView) findViewById(R.id.services);

        String [] services = new String[]{"Transaction","Transfer Money","Loan","Payments","Bills","Savings","Investment","Updates","Account"};

        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.activity_list_item, android.R.id.text1, services);
                listview.setAdapter(adapter);
         listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int service, long l) {
                if(service==0){
                    Intent intent = new Intent(Task.this,Transaction.class);
                    startActivity(intent);
                }
                if(service==1){
                    Intent intent = new Intent(Task.this,Transfer.class);
                    startActivity(intent);
                }
                if(service==2){
                    Intent intent = new Intent(Task.this,Loan.class);
                    startActivity(intent);
                }
                if(service==3){
                    Intent intent = new Intent(Task.this,Payments.class);
                    startActivity(intent);
                }
                if(service==4){
                    Intent intent = new Intent(Task.this,Bills.class);
                    startActivity(intent);
                }
                 if(service==5){
                     Intent intent = new Intent(Task.this,Savings.class);
                     startActivity(intent);
                 }
                if(service==6){
                    Intent intent = new Intent(Task.this,Investment.class);
                    startActivity(intent);
                }
                 if(service==7){
                     Intent intent = new Intent(Task.this,Updates.class);
                     startActivity(intent);
                 }
                 if(service==8){
                     Intent intent = new Intent(Task.this,account.class);
                     startActivity(intent);
                 }

            }

        });
    }
}
