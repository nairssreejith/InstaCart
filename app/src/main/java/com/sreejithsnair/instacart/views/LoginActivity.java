package com.sreejithsnair.instacart.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sreejithsnair.instacart.R;
import com.sreejithsnair.instacart.model.LoginResponse;
import com.sreejithsnair.instacart.model.ProductListModel;
import com.sreejithsnair.instacart.repositories.ProductListRepository;
import com.sreejithsnair.instacart.requests.LoginRequest;
import com.sreejithsnair.instacart.requests.ProductDetailsRequest;
import com.sreejithsnair.instacart.viewmodel.ProductListViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtEmail;
    TextInputEditText edtPassword;

    Button btnLogin;

    String email;
    String password;

    ProductListViewModel productListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username or Password not entered.", Toast.LENGTH_LONG).show();
                } else {
                    email = edtEmail.getText().toString();
                    password = edtPassword.getText().toString();

                    login(email, password);
                }
            }
        });
    }

    private void init(){
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    public void login(String email, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        productListViewModel.logIn(loginRequest).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"Successfully logged in", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }, 800);
            }
        });

    }
}