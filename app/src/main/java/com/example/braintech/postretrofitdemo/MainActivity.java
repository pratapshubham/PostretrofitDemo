package com.example.braintech.postretrofitdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LoginInterface apiInterface;
    EditText edt_user,edt_passwrd;
    Button btn_login;
    String username,password;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getId();
            apiInterface = LoginAPI.getRetrofit().create(LoginInterface.class);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edt_user.getText().toString();
                password = edt_passwrd.getText().toString();
                if (Validate())
                {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("username",username);
                        jsonObject.put("password",password);
                        jsonObject.put("token","");
                        jsonObject.put("devicetype","");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (jsonObject.toString()));

                    Call<LoginModel> getRegisterMessage = apiInterface.getAccess("application/json", "no-cache", body);


                    getRegisterMessage.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful())
                            {
                            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public Boolean Validate()
    {
        if (username.isEmpty())
        {
            Toast.makeText(this,"Enter Username",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.isEmpty())
        {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void getId()
    {
        edt_user = (EditText)findViewById(R.id.edt_username);
        edt_passwrd = (EditText)findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btnLogin);
    }
}
