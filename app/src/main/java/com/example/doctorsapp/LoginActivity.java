package com.example.doctorsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            // TODO: create file dates.txt empty
            FileWriter writerDates = new FileWriter(new File(getFilesDir(), "dates.txt"));
            writerDates.flush();
            writerDates.close();

            File file = new File(getFilesDir(), "users.txt");
            FileWriter writerUsers = new FileWriter(file);
            writerUsers.append("Armando Salazar:armando@email.com:12345");
            writerUsers.flush();
            writerUsers.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        findViewById(R.id.buttonLogin).setOnClickListener(v -> login());


        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    public String[] getData() throws IOException {
        File file = new File(getFilesDir(), "users.txt");

        StringBuilder text = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }

        bufferedReader.close();

        String[] data = text.toString().split(":");

        for (String value : data) {
            System.out.println(value);
            Log.e(TAG, value);
        }

        return data;
    }

    private void login() {
        try {
            String[] data = getData();
            for (String value :
                    data) {
                System.out.println(value);
            }
            if (data[1].equals(inputEmail.getText().toString().trim()) && data[2].equals(inputPassword.getText().toString().trim())) {
                Toast.makeText(this, "Sesión Iniciada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Verifique sus credenciales", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}