package com.example.campusexpense;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    private EditText edtNumber1, edtNumber2, edtResult;
    private Button btnSumNumber, btnClearData;
    private TextView tvHistory;
    private String history = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        edtResult = findViewById(R.id.edtResult);
        btnSumNumber = findViewById(R.id.btnSumNumber);
        btnClearData = findViewById(R.id.btnClearData);
        tvHistory = findViewById(R.id.tvHistory);
        edtResult.setEnabled(false);

        // lay du lieu tu Shared Preferences
        SharedPreferences myPrefs = getSharedPreferences("saveMaths", MODE_PRIVATE);
        history = myPrefs.getString("myHistory", "");
        tvHistory.setText(history);

        btnSumNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(edtNumber1.getText().toString().trim());
                int number2 = Integer.parseInt(edtNumber2.getText().toString().trim());
                int result = number1 + number2;
                edtResult.setText(String.format("%d", result));
                history += number1 + " + " + number2 + " = " + result;
                tvHistory.setText(history);
                history += "\n";
            }
        });
        btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = "";
                tvHistory.setText(history);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // xu ly luu data vao Shared Preference
        SharedPreferences myPrefs = getSharedPreferences("saveMaths", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.putString("myHistory", history);
        myEditor.apply();
    }
}
