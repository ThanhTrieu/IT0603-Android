package com.example.campusexpense;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class IntentDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);
        EditText edtUrl = findViewById(R.id.edtUrl);
        Button btnUrl = findViewById(R.id.btnUrl);
        EditText edtPhone = findViewById(R.id.edtPhone);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnGotoActivity = findViewById(R.id.btnGoToActivity);
        Button btnSendData = findViewById(R.id.btnSendData);
        TextView tvUser = findViewById(R.id.tvUsername);
        TextView tvPass = findViewById(R.id.tvPassword);

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();
        if(myBundle != null){
            String user = myBundle.getString("MY_USER", "");
            String pass = myBundle.getString("MY_PASS", "");
            tvUser.setText(user);
            tvPass.setText(pass);
        }

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUrl = edtUrl.getText().toString().trim();
                String myPhone = edtPhone.getText().toString().trim();
                Intent intent = new Intent(IntentDemoActivity.this, IntentDemoSecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MY_PHONE", myPhone);
                bundle.putString("MY_URL", myUrl);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnGotoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentDemoActivity.this, IntentDemoSecondActivity.class);
                startActivity(intent); // chuyen sang man hinh activity IntentDemoSecondActivity
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myPhoneNumber = edtPhone.getText().toString().trim();
                if(TextUtils.isEmpty(myPhoneNumber)){
                    Toast.makeText(IntentDemoActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL); // call phone
                    intent.setData(Uri.parse("tel:"+myPhoneNumber));
                    if(ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        startActivity(intent);
                    } else {
                        requestPermissions(new String[]{ CALL_PHONE }, 1);
                    }
                }
            }
        });
        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString().trim();
                if(TextUtils.isEmpty(url)){
                    Toast.makeText(IntentDemoActivity.this, "Enter URL", Toast.LENGTH_SHORT).show();
                } else {
                    // load url website
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
    }
}
