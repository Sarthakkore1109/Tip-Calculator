// Author
// Sarthak Kore
// #110238912

package com.sarthakkore.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalculate = findViewById(R.id.buttonCalculate);
        Button btnClear = findViewById(R.id.buttonReset);

        EditText textTotalAmount = findViewById(R.id.editTextTotalAmount);
        EditText textTaxAmount = findViewById(R.id.editTextTaxAmount);
        RadioGroup radioGroupTipPercent = findViewById(R.id.radiogroupp);
        RadioButton radioButtonDefault = findViewById(R.id.radioButton0);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroupTipPercent.getCheckedRadioButtonId();
                RadioButton radioButtonSelectedTip = findViewById(selectedId);
                DecimalFormat twoDForm = new DecimalFormat("#.##");

                String tipPercentString = "";
                String totalAmount = "";
                String taxAmount = "";

                tipPercentString = radioButtonSelectedTip.getText().toString();
                Log.i("radio ID",tipPercentString);

                int tipPercent = 0;

                if(tipPercentString.equals("5 %")){
                    tipPercent = 5;
                }else if (tipPercentString.equals("10 %")){
                    tipPercent = 10;
                }else if (tipPercentString.equals("20 %")){
                    tipPercent = 20;
                }else{
                    tipPercent = 0;
                }

                Log.i("tip percent",""+tipPercent);

                totalAmount = textTotalAmount.getText().toString();
                taxAmount = textTaxAmount.getText().toString();

                try {
                    Log.i("totalAmount",""+totalAmount);
                    Log.i("TaxAmount",""+taxAmount);

                    double tip = (Double.parseDouble(totalAmount)*tipPercent)/100;
                    double grandTotal = tip + Double.parseDouble(totalAmount) + Double.parseDouble(taxAmount);

                    Log.i("GrandTotal",""+grandTotal);
                    Log.i("tip",""+tip);

                    double grandTotalResult = Double.parseDouble(twoDForm.format(grandTotal));

                    TextView tipAmount = findViewById(R.id.textViewTotalTip);
                    TextView GrandAmount = findViewById(R.id.textViewGrandAmount);

                    String tipResult = getString(R.string.tipAmountResult);
                    String totalResult = getString(R.string.totalAmountResult);

                    tipAmount.setText(tipResult+tip);
                    GrandAmount.setText(totalResult+grandTotalResult);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please fill the blank fields.",Toast.LENGTH_LONG).show();
                    Log.e("Exception", e.toString());
                }



            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTotalAmount.getText().clear();
                textTaxAmount.getText().clear();
                radioButtonDefault.setChecked(true);

                TextView tipAmount = findViewById(R.id.textViewTotalTip);
                TextView GrandAmount = findViewById(R.id.textViewGrandAmount);
                tipAmount.setText(R.string.tipAmount);
                GrandAmount.setText(R.string.totalAmount);

            }
        });


    }
}