package com.example.kondratkov.bookingofmeetingrooms.view.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.editTextRegistrName)EditText editTextRegistrName;
    @BindView(R.id.editTextRegistrPassword)EditText editTextRegistrPassword;
    private static ApiInterface mApiInterface;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        mApiInterface = Controller.getApi();
    }

    @OnClick(R.id.buttonRegistr)
    public void onClickReg(View view){
        if(editTextRegistrName.length()!=0 && editTextRegistrPassword.length()!=0){
            user = new User();
            user.setUserName(String.valueOf(editTextRegistrName.getText()));
            user.setPassword(String.valueOf(editTextRegistrPassword.getText()));
            onRequest();
        }else{
            Toast.makeText(RegistrationActivity.this, "Не все поля заполненны!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequest(){
        mApiInterface.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String s = String.valueOf(response.body());
                if(response.code()>199 && response.code()<300 && response.body()!=null){
                    user = response.body();
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            MyApplication.getInstance().getRepository().addUser(user);
                            RegistrationActivity.this.finish();
                        }
                    });
                }else{
                    Toast.makeText(RegistrationActivity.this, "ошибка связи!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<User>call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "ошибка связи!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
