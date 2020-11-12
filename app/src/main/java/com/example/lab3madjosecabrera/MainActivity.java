package com.example.lab3madjosecabrera;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;





public class MainActivity extends AppCompatActivity
{
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(0.0f, 0.0f);


        setContentView(R.layout.activity_main);

        billEditText = (EditText) findViewById(R.id.meal_Price_Value);
        tipEditText = (EditText) findViewById(R.id.tip_Percent_Value);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    public void calculate()
    {
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = (TextView) findViewById(R.id.tip_Amount);
        TextView totalTextView = (TextView) findViewById(R.id.total_Value);

        try
        {
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            tipCalc.setBill(billAmount);
            tipCalc.setTip(0.01f * tipPercent);

            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        }
        catch(NumberFormatException nfe)
        {

        }
    }

    private class TextChangeHandler implements TextWatcher
    {
        public void afterTextChanged(Editable e) { calculate(); }

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int count, int after)
        {

        }
    }
}