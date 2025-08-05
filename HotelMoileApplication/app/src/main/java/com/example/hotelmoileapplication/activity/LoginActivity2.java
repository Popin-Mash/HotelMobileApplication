package com.example.hotelmoileapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelmoileapplication.R;
import com.example.hotelmoileapplication.constants.Constants;
import com.example.hotelmoileapplication.data.local.UserSharedPreference;
import com.example.hotelmoileapplication.models.User;
import com.example.hotelmoileapplication.utils.MessageUtils;

public class LoginActivity2 extends AppCompatActivity {

    private EditText etUsername,etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    private void initView(){
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            MessageUtils.showToastMessage(this, "Please enter username");
            return;
        }
        if (password.isEmpty()) {
            MessageUtils.showToastMessage(this, "Please enter password");
            return;
        }

        if (!username.equals(Constants.USER_NAME_DB_LOCAL)) {
            MessageUtils.showToastMessage(this, "Username is incorrect");
            return;
        }
        if (!password.equals(Constants.USER_PASSWORD_DB_LOCAL)) {
            MessageUtils.showToastMessage(this, "Password is incorrect");
            return;
        }

        MessageUtils.showToastMessage(this, "Login Successfully");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserSharedPreference.saveUserAccess(user, this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}