package com.a2xb3_finalproject.harsh.a2xb3_finalproject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.InputStream;
import java.util.ArrayList;

import backend.adts.Product;
import backend.file_processing.Data;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button searchButton;

    private EditText productString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Resources rs = getResources();
        try {
            Data.init(rs.openRawResource(R.raw.dataset_refined9));
        } catch(Exception e) {
            System.out.println(" ScanShop: An error occured reading the file. Woops! ");
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;

        searchButton = (Button) this.findViewById(R.id.searchBTN);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productString = (EditText)findViewById(R.id.searchTextField);
                String s = productString.getText().toString();
                Object[] prods = Data.searchByTitle(s, 5);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(((Product) prods[0]).toStringForAndroid());
                textView.setMovementMethod(new ScrollingMovementMethod());

                TextView textView2 = (TextView) findViewById(R.id.textView2);
                textView2.setText(((Product) prods[1]).toStringForAndroid());
                textView2.setMovementMethod(new ScrollingMovementMethod());

                TextView textView3 = (TextView) findViewById(R.id.textView3);
                textView3.setText(((Product) prods[2]).toStringForAndroid());
                textView3.setMovementMethod(new ScrollingMovementMethod());

                TextView textView4 = (TextView) findViewById(R.id.textView4);
                textView4.setText(((Product) prods[3]).toStringForAndroid());
                textView4.setMovementMethod(new ScrollingMovementMethod());

                TextView textView5 = (TextView) findViewById(R.id.textView5);
                textView5.setText(((Product) prods[4]).toStringForAndroid());
                textView5.setMovementMethod(new ScrollingMovementMethod());
            }
//            public void onClick(View view) {
//                productString = (EditText)findViewById(R.id.searchTextField);
//                String s = productString.getText().toString();
//                Object[] prods = Data.searchByTitle(s, 5);
//                ArrayList<String> productName = new ArrayList<String>();
//                for (int i = 0; i < prods.length; i++) {
//                    productName.add(i, ((Product) prods[i]).toString());
//                }
//                ListView list = (ListView)findViewById(R.id.listView);
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.activity_main, R.id.listView, productName);
//                list.setAdapter(adapter);
//            }
        });

        button = (Button) this.findViewById(R.id.scanBTN);

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

                if (o != null) {
                    Toast.makeText(this, "Scanned: " + ((Product) o).toStringForAndroid(), Toast.LENGTH_LONG).show();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(((Product) o).toStringForAndroid());
                }
                else
                    Toast.makeText(this, "Could not find product. Woops!", Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//    protected void onButtonClick(View view) {
//        productString = (EditText)findViewById(R.id.searchTextField);
//        Object[] prods = Data.searchByTitle(productString.toString(), 5);
//        ArrayList<String> productName = new ArrayList<String>();
//        for (int i = 0; i < prods.length; i++) {
//            productName.add(i, ((Product) prods[i]).toString());
//        }
//        ListView list = (ListView)findViewById(R.id.listView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_main, R.id.txtitem, productName);
//        list.setAdapter(adapter);
//    }

}
