package com.example.campusexpense;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button loginButton;
    private ProgressBar loadingLogin;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
    }

    private void login(){
        mUsername = findViewById(R.id.username); // tim den EditText
        mPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        // bat su kien khi nguoi dung bam vao button ok
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay du lieu nguoi dung nhap vao EditTex
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                // kiem tra xem nguoi dung nhap du lieu chua?
                if(TextUtils.isEmpty(username)){
                    mUsername.setError("Username cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password cannot be empty");
                    return;
                }
                if (isProgressVisible){
                    loginButton.setText("Processing ...");
                    // on below line we are changing
                    // its visibility
                    loadingLogin.setVisibility(View.GONE);
                }
                if(username.equals("it0603") && password.equals("123456789")){
                    isProgressVisible = true;
                    // xu ly lam gi do tiep
                    // quay sang trang Main Activity
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    isProgressVisible = false;
                    // thong bao message loi
                    loadingLogin.setVisibility(View.VISIBLE);
                    mUsername.setError("username invalid");
                    mPassword.setError("password invalid");
                }
            }
        });

    }
}
