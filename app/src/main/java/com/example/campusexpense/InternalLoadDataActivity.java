package com.example.campusexpense;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;

public class InternalLoadDataActivity extends AppCompatActivity {
    private TextView tvIdStudent, tvNameStudent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_data);
        tvIdStudent = findViewById(R.id.tvIDStudent);
        tvNameStudent = findViewById(R.id.tvNameStudent);
    }
    public void loadData(View view){
        try {
            FileInputStream fileInputStream = openFileInput("code.txt");
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = fileInputStream.read()) != -1){
                builder.append((char)read);
            }
            String id = builder.substring(0, builder.indexOf(" "));
            String name = builder.substring(builder.indexOf(" ")+1);
            tvIdStudent.setText(id);
            tvNameStudent.setText(name);
            Toast.makeText(this, "Load Success", Toast.LENGTH_SHORT).show();
        } catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Load Fail", Toast.LENGTH_SHORT).show();
        }
    }
    public void backData(View view){

    }
}
