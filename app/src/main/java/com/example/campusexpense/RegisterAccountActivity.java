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

public class RegisterAccountActivity extends AppCompatActivity {
    private EditText edtUserAccount, edtPassAccount, edtEmailAccount, edtPhoneAccount;
    private Button btnRegister;
    private AccountDatabase accountDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        edtUserAccount = findViewById(R.id.edtUserAccount);
        edtPassAccount = findViewById(R.id.edtPassAccount);
        edtEmailAccount = findViewById(R.id.edtEmailAccount);
        edtPhoneAccount = findViewById(R.id.edtPhoneAccount);
        btnRegister = findViewById(R.id.btnRegisterAccount);
        accountDatabase = new AccountDatabase(RegisterAccountActivity.this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
                Intent intent = new Intent(RegisterAccountActivity.this, LoginAccountActivity.class);
                startActivity(intent);
            }
        });
    }
    public void signUp(){
        String user = edtUserAccount.getText().toString().trim();
        String password = edtPassAccount.getText().toString().trim();
        String email = edtEmailAccount.getText().toString().trim();
        String phone = edtPhoneAccount.getText().toString().trim();
        if (TextUtils.isEmpty(user)){
            edtUserAccount.setError("Username can be not empty");
            return;
        }
        if (TextUtils.isEmpty(password)){
            edtPassAccount.setError("Password can be not empty");
            return;
        }
        if (TextUtils.isEmpty(email)){
            edtEmailAccount.setError("Email can be not empty");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            edtPhoneAccount.setError("Phone number can be not empty");
            return;
        }
        long result = accountDatabase.addNewAccount(user, password, email, phone);
        if (result == -1) {
            Toast.makeText(RegisterAccountActivity.this, "Insert Account Failure", Toast.LENGTH_LONG).show();
            return;
        } else {
            Toast.makeText(RegisterAccountActivity.this, "Insert Account Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
