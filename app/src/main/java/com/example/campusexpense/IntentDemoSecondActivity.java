package com.example.campusexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentDemoSecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo_second);
        Button btnActivity = findViewById(R.id.btnComebackActivity);
        TextView tvUrl = findViewById(R.id.tvUrl);
        TextView tvPhone = findViewById(R.id.tvPhone);
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText edtUser = findViewById(R.id.edtUsername);
        EditText edtPass = findViewById(R.id.edtPassword);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            // lay du lieu dc gui sang
            String url = bundle.getString("MY_URL", "");
            String phone = bundle.getString("MY_PHONE", "");
            tvUrl.setText(url);
            tvPhone.setText(phone);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if(checkLoginUser(user, pass)){
                    // chuyen sang man hinh activity khac
                    Intent myIntent = new Intent(IntentDemoSecondActivity.this, IntentDemoActivity.class);
                    Bundle myBundle = new Bundle();
                    myBundle.putString("MY_USER", user);
                    myBundle.putString("MY_PASS", pass);
                    myIntent.putExtras(myBundle);
                    startActivity(myIntent);
                    finish();
                } else {
                    edtUser.setError("Username invalid");
                    edtPass.setError("Password invalid");
                    Toast.makeText(IntentDemoSecondActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentDemoSecondActivity.this, IntentDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkLoginUser(String user, String password){
        if(user.equals("admin") && password.equals("123")){
            return true;
        }
        return false;
    }
}
