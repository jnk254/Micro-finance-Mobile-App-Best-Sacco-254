package com.example.bsacco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;

import io.opencensus.internal.Utils;

public class Accdetails extends AppCompatActivity {

    public static final String downloadPdfUrl = "http://androhub.com/demo/demo.pdf";

    private TextView viewbills;
    private Button download;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accdetails);
        getBill();
        viewbills = findViewById(R.id.dispalybills);
        download = findViewById(R.id.btn_accdetails);

        Intent intent =getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        viewbills.setText(text);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*switch (view.getId()) {
                    case R.id.btn_accdetails:
                        if (isConnectingToInternet())
                            new DownloadTask(Accdetails.this, download, Utils.downloadPdfUrl);
                        else
                            Toast.makeText(Accdetails.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }
        });


        //Open downloaded folder
      /*  private void openDownloadedFolder(){
            //First check if SD Card is present or not
            if (new Transact().isSDCardPresent()) {

                //Get Download Directory File
                File apkStorage = new File(
                        Environment.getExternalStorageDirectory() + "/"
                                + Utils.downloadDirectory);

                //If file is not present then display Toast
                if (!apkStorage.exists())
                    Toast.makeText(Accdetails.this, "Right now there is no directory. Please download some file first.", Toast.LENGTH_SHORT).show();

                else {

                    //If directory is present Open Folder

                    /** Note: Directory will open only if there is a app to open directory like File Manager, etc.  **/

                    /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                            + "/" + Utils.downloadDirectory);
                    intent.setDataAndType(uri, "file/*");
                    startActivity(Intent.createChooser(intent, "Open Download Folder"));
                }

            } else
                Toast.makeText(Accdetails.this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

        }

    } */

    /*private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    } */

}

    private void getBill() {

        /*DocumentReference billdetails = db.collection("Bills").document();
        billdetails.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append(("Bill Amount: "));
                    fields.append(("\nBill Type: "));
                    viewbills.setText(fields.toString());
                }
            }
        });*/
    }
}


