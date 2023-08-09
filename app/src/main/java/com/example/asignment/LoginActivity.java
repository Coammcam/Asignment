package com.example.asignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asignment.DAO.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {

    private NguoiDungDAO nguoiDungDAO;

    private TextView signUp;
    private Button btnLogin;
    private EditText edtUser;
    private EditText edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edt_Username);
        edtPass = findViewById(R.id.edt_Password);

        signUp = (TextView) findViewById(R.id.tv_SignUp);
        SignUp();

        btnLogin = findViewById(R.id.btn_Login);
        Login();


    }
    public void SignUp() {
        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    public void Login() {
        btnLogin.setOnClickListener(view -> {
            String user = edtUser.getText().toString();
            String pass = edtPass.getText().toString();

            nguoiDungDAO = new NguoiDungDAO(this);
            boolean check = nguoiDungDAO.CheckLogin(user, pass);
            if (check == true) {
                Toast.makeText(this, "Login thanh cong", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Login that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}