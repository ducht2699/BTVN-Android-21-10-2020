package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Currency> currencies;
    EditText edtAmount;
    TextView tvResult;

    double dAmount;

    private double fromCoefficient;
    double toCoefficient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencies = new ArrayList<>();
        currencies.add(new Currency("USD", 23177.00));
        currencies.add(new Currency("JPY", 221.36));
        currencies.add(new Currency("EUR", 27493.72));
        currencies.add(new Currency("THB", 740.24));
        currencies.add(new Currency("GBP", 30235.53));
        currencies.add(new Currency("SGD", 17072.04));
        currencies.add(new Currency("HKD", 2990.50));
        currencies.add(new Currency("AUD", 16546.06));
        currencies.add(new Currency("TWD", 809.55));
        currencies.add(new Currency("VND", 1.00));


        CurrencyAdapter adapter1 = new CurrencyAdapter(currencies, this);

        Spinner spnFrom;

        spnFrom = findViewById(R.id.spnFrom);
        spnFrom.setAdapter(adapter1);
        spnFrom.setSelection(9);
        spnFrom.setOnItemSelectedListener(this);

        CurrencyAdapter adapter2 = new CurrencyAdapter(currencies, this);

        Spinner spnTo;

        spnTo = findViewById(R.id.spnTo);
        spnTo.setAdapter(adapter2);
        spnTo.setSelection(0);
        spnTo.setOnItemSelectedListener(this);

        tvResult = (TextView) findViewById(R.id.tvResult);
        edtAmount = (EditText) findViewById(R.id.edtAmount);
        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!edtAmount.getText().toString().equals("")) {
                    dAmount = Double.valueOf(s.toString());

                    update();


                } else {
                    tvResult.setText("");
                }

            }
        });

        Button btnSwap = (Button) findViewById(R.id.btnSwap);
        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dTemp = fromCoefficient;
                fromCoefficient = toCoefficient;
                toCoefficient = dTemp;

                int iTempIndex = spnFrom.getSelectedItemPosition();
                spnFrom.setSelection(spnTo.getSelectedItemPosition());
                spnTo.setSelection(iTempIndex);

                update();
            }
        });
    }

    public  void update() {
        double dCalulated = fromCoefficient * dAmount / toCoefficient;
        tvResult.setText(String.valueOf(dCalulated));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spnFrom:
                this.fromCoefficient = currencies.get(position).getCoefficient();
                Log.v("TAG", (String.valueOf(fromCoefficient)));

                break;
            case R.id.spnTo:
                this.toCoefficient = currencies.get(position).getCoefficient();
                Log.v("TAG", (String.valueOf(toCoefficient)));
                break;
        }
        update();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}