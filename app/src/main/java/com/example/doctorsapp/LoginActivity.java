package com.example.doctorsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.buttonLogin).setOnClickListener(v -> login());

        File file = new File(LoginActivity.this.getFilesDir(), "text");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File f = new File(file, "sample");
            FileWriter writer = new FileWriter(f);
            writer.append("Armando Salazar:armando@email.com:12345");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    public String[] getData() throws IOException {
        File fileEvents = new File(LoginActivity.this.getFilesDir().toString().concat("/text/sample"));
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileEvents));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        br.close();
        System.out.println("[[".concat(text.toString()).concat("]]"));
        String data[] = text.toString().split(":");
        for (String value : data) {
            System.out.println(value);
        }
        System.out.println(LoginActivity.this.getFilesDir());
        return data;
    }

    private void login() {
        try {
            String data[] = getData();
            for (String value :
                    data) {
                System.out.println(value);
            }
            if (data[1].equals(inputEmail.getText().toString().trim()) && data[2].equals(inputPassword.getText().toString().trim())) {
                Toast.makeText(this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}