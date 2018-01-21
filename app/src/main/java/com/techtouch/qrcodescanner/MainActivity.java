package com.techtouch.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.techtouch.barcodescanner.R;

public class MainActivity extends AppCompatActivity {


    private TextView txtResult;
   private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txt_result);

        //intializing scan object
        qrScan = new IntentIntegrator(this);
        qrScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if barcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    txtResult.setText(result.getContents());
                }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startScan(View view) {
        //initiating the qr code scan
        txtResult.setText("");
        qrScan.initiateScan();
    }
}