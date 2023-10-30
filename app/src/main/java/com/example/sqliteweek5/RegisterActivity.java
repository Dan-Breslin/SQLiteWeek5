package com.example.sqliteweek5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText regEmail,regPassword,regConfirm;
    Button registerNew;
    // This isnt finished just moving code but follow sheet.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        regEmail = (EditText) findViewById(R.id.editTxt_RegEmail);
        regPassword = (EditText) findViewById(R.id.editText_RegPassword);
        regConfirm = (EditText) findViewById(R.id.editText_RegPassConfirm);

        registerNew = (Button) findViewById(R.id.btn_RegRegister);
        registerNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUserEmail = regEmail.getText().toString();
                String newUserPassword = regPassword.getText().toString();
                String newUserConfirm = regConfirm.getText().toString();


                if (newUserEmail.equals("") || newUserPassword.equals("") || newUserConfirm.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (newUserPassword.equals(newUserConfirm)) {
                        Boolean checkEmail = db.checkEmail(newUserEmail);
                        if (checkEmail == false) {
                            Boolean insert = db.insert(newUserEmail, newUserPassword);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}