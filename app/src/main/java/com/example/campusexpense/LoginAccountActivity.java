package com.example.campusexpense;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpense.DatabaseSQLite.AccountDatabase;
import com.example.campusexpense.Model.Account;

public class LoginAccountActivity extends AppCompatActivity {
    public AccountDatabase accountDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        EditText edtUser = findViewById(R.id.edtAccount);
        EditText edtPass = findViewById(R.id.edtAccountPassword);
        accountDatabase = new AccountDatabase(LoginAccountActivity.this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xu ly dang nhap
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (TextUtils.isEmpty(user)){
                    edtUser.setError("Username can be not empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPass.setError("Password can be not empty");
                    return;
                }

                Account data = accountDatabase.getInfoUser(user, pass);
                assert data != null;
                if (data.getEmail() != null){
                    // lay dc data : dang nhap chinh xac
                    String email = data.getEmail();
                    Toast.makeText(LoginAccountActivity.this, email, Toast.LENGTH_LONG).show();
                } else {
                    // dang nhap sai
                    Toast.makeText(LoginAccountActivity.this, "Account invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAccountActivity.this, RegisterAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
