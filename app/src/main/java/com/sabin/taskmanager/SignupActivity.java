package com.sabin.taskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sabin.taskmanager.api.Userapi;
import com.sabin.taskmanager.model.User;
import com.sabin.taskmanager.serverresponse.SignupResponse;
import com.sabin.taskmanager.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private ImageView img;
    private EditText etfname, etlname, etunmae, etpass, etcpass;
    private Button btnsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        img = findViewById(R.id.uimg);
        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        etunmae = findViewById(R.id.etuname);
        etpass = findViewById(R.id.etpass);
        etcpass = findViewById(R.id.etcpass);
        btnsignup = findViewById(R.id.btnsigup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etpass.getText().toString().equals((etcpass.getText().toString()))){
//                    saveImageOnly();
                    signup();
                }else
                {
                    Toast.makeText(SignupActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etpass.requestFocus();
                    return;
                }

            }
        });

    }

    private void signup() {

        String fname = etfname.getText().toString();
        String lname  = etlname.getText().toString();
        String Username = etunmae.getText().toString();
        String Password = etpass.getText().toString();


        User user = new User(fname,lname,Username,Password);
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.resgisterUser(user);

        signupResponseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
