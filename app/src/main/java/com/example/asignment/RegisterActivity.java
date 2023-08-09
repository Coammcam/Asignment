package com.example.asignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asignment.DAO.NguoiDungDAO;

public class RegisterActivity extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    private ImageView back;
    private EditText edtUser;
    private EditText edtPass;
    private Button btnSignUp;
    private EditText edtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUser = findViewById(R.id.edt_User);
        edtPass = findViewById(R.id.edt_Pass);
        edtFullName = findViewById(R.id.edt_Fullname);

        btnSignUp = findViewById(R.id.btn_SignUp);
        SignUp_Register();

        back = findViewById(R.id.btn_Back);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    public void SignUp_Register() {
        btnSignUp.setOnClickListener(view -> {
            String user = edtUser.getText().toString();
            String pass = edtPass.getText().toString();
            String fullName = edtFullName.getText().toString();

            nguoiDungDAO = new NguoiDungDAO(this);

            boolean check = nguoiDungDAO.Register(user, pass, fullName);
            if (check == true) {
                Toast.makeText(this, "Register thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Register that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}