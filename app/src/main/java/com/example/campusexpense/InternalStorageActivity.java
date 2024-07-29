package com.example.campusexpense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class InternalStorageActivity extends AppCompatActivity {
    private EditText edtId, edtFullName;
    private TextView tvPathFile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        edtId = findViewById(R.id.edtId);
        edtFullName = findViewById(R.id.edtFullName);
        tvPathFile = findViewById(R.id.tvPathFile);
    }
    public void saveData(View view){
        File file = null;
        String idStudent = edtId.getText().toString().trim();
        String fullName = edtFullName.getText().toString().trim();
        // luu du lieu vao internal Storage nghia la luu vao file trong thiet bi dien thoai
        // file do co dinh dang la .xml
        FileOutputStream fileOutputStream = null;
        try {
            idStudent = idStudent + " ";
            file = getFilesDir(); // lay thu muc chua file ?
            fileOutputStream = openFileOutput("code.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(idStudent.getBytes());
            fileOutputStream.write(fullName.getBytes());
            edtId.setText("");
            edtFullName.setText("");
            Toast.makeText(InternalStorageActivity.this, "Done", Toast.LENGTH_SHORT).show();
            String fullPath = "Path : " + file + "\\code.txt";
            tvPathFile.setText(fullPath);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    public void nextData(View view){
        Intent intent = new Intent(InternalStorageActivity.this, InternalLoadDataActivity.class);
        startActivity(intent);
    }
}
