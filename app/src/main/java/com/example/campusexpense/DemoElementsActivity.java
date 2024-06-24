package com.example.campusexpense;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DemoElementsActivity extends AppCompatActivity {
    private CheckBox AgreeYes;
    private CheckBox AgreeNo;
    private Button ClickMe;
    private RadioButton Female;
    private RadioButton Male;

    private final String FlagTag = "DemoElementsActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_elements);
        findViewById(R.id.btn_click_me).setEnabled(false);
        findViewById(R.id.btn_gender).setEnabled(false);

        AgreeYes = findViewById(R.id.cb_yes);
        AgreeNo = findViewById(R.id.cb_no);
        // bat su kien nguoi dung check vao checkBox
        AgreeYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClickMe = findViewById(R.id.btn_click_me);
                if(isChecked){
                    // da tich vao checkbox
                    ClickMe.setEnabled(true); // mo cho phep bam button

                    Log.i(FlagTag, "Checkbox is checked");
                } else {
                    // nguoi dung chua tich vao checkbox
                    ClickMe.setEnabled(false); // khoa lai
                    Log.i(FlagTag, "Checkbox is not checked");
                }
            }
        });

        Female = findViewById(R.id.rad_female);
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.btn_gender).setEnabled(true);
                Log.i(FlagTag, "Radio Button Female is checked");
            }
        });
    }

    public void handleClickMe(View view){
        // bat su kien nguoi dung bam Button
        AgreeNo = findViewById(R.id.cb_no);
        if(AgreeNo.isChecked()){
            Toast.makeText(this, "Checkbox is checked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Checkbox is not checked", Toast.LENGTH_SHORT).show();
        }
    }
}
