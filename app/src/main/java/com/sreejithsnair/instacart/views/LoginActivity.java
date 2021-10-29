package com.sreejithsnair.instacart.views;

import androidx.appcompat.app.AppCompatActivity;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtEmail;
    TextInputEditText edtPassword;

    Button btnLogin;

    String email;
    String password;


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
        edtEmail.setText("mtest1@mobatia.com");
        edtPassword = findViewById(R.id.edt_password);
        edtPassword.setText("12345678");
        btnLogin = findViewById(R.id.btn_login);
    }

    public void login(String email, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        ProductDetailsRequest productDetailsRequest = new ProductDetailsRequest();
        productDetailsRequest.setStart(1);
        productDetailsRequest.setLimit(20);

        Call<LoginResponse> loginResponseCall = ProductListRepository.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    //startList("Bearer "+ loginResponse.getToken(), productDetailsRequest);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("token", loginResponse.getToken()));
                        }
                    }, 800);
                }
                else

                    Toast.makeText(LoginActivity.this, "What", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        }

    public void startList(String token, ProductDetailsRequest productDetailsRequest){
        Call<ProductListModel> productListModelCall = ProductListRepository.getUserService().getProductList(token,productDetailsRequest);
        productListModelCall.enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response1) {
                if(response1.code() == 200){
                    ProductListModel productListModel = response1.body();
                    Log.d("MOOOOSE", "success...." + productListModel.getResponseArray().toString());
                }
                else
                    Log.d("MOOOOSE", "Something Fishy");
            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {

            }
        });
    }
}