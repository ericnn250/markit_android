package com.markit.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText phone,password,username;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone=(EditText) findViewById(R.id.register_phone);
        password=(EditText) findViewById(R.id.register_password);
        username=(EditText) findViewById(R.id.register_username);
        register=(TextView) findViewById(R.id.register_button);
    }
}