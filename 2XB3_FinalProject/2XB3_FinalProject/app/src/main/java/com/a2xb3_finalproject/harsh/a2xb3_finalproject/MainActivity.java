package com.a2xb3_finalproject.harsh.a2xb3_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.InputStream;

import backend.adts.Product;
import backend.file_processing.Data;


public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Resources rs = getResources();
        AssetManager am = getAssets();

        try {
            Data.init(rs.openRawResource(R.raw.amazon_rawdata));
        } catch(Exception e) {
            System.out.println("ERROR ================================ ");
            e.printStackTrace();
        }
        /*
        try {
            System.out.println(" OUTPUT: ");
            for (String s : am.list("")) System.out.println(s);
            System.out.println(" OUTPUT: ");
            //Data.init(am.open("amazon_rawdata.json"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        */


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) this.findViewById(R.id.scanBTN);
        final Activity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");

                String res = result.getContents();
                Object o = Data.searchByBarcode(Long.parseLong(res));

                if (o != null)
                    Toast.makeText(this, "Scanned: " + ((Product) o).toString(), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Could not find product. Woops!", Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
