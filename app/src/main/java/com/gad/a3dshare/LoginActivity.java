package com.gad.a3dshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gad.a3dshare.model.LoginResponse;
import com.gad.a3dshare.service.LoginService;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;

    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        loginService = new LoginService();
    }

    private void initViews() {
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        login = findViewById(R.id.login_button);
    }

    public void onLoginClicked(View v) {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        LoginResponse response = loginService.login(emailText,passwordText);

        Toast.makeText(LoginActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
    }
}
