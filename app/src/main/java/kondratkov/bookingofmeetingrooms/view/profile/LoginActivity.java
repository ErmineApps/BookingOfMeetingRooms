package kondratkov.bookingofmeetingrooms.view.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kondratkov.bookingofmeetingrooms.MyApplication;
import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import kondratkov.bookingofmeetingrooms.model.api.Controller;
import kondratkov.bookingofmeetingrooms.model.pojo.User;
import kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextLoginName)EditText editTextLoginName;
    @BindView(R.id.editTextLoginPassword)EditText editTextLoginPassword;
    private static ApiInterface mApiInterface;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mApiInterface = Controller.getApi();
    }

    @Override
    public void onStart(){
        super.onStart();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (MyApplication.getInstance().getRepository().getTestUser() == 1) {
                    Intent intent = new Intent(LoginActivity.this, ListRoomsActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        });

    }

    @OnClick(R.id.buttonLoginEntry)
    public void onClickLogin(View view){
        if(editTextLoginName.length()!=0 && editTextLoginPassword.length()!=0){
            user = new User();
            user.setUserName(String.valueOf(editTextLoginName.getText()));
            user.setPassword(String.valueOf(editTextLoginPassword.getText()));
            onRequest();
        }else{
            Toast.makeText(LoginActivity.this, "Не все поля заполненны!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.buttonLoginRegistr)
    public void onClickRegis(View view){
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onRequest(){
        mApiInterface.authentication(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String s = String.valueOf(response.body());
                if(response.code()>199 && response.code()<300 && response.body()!=null){
                    User user = response.body();
                    Intent intent = new Intent(LoginActivity.this, ListRoomsActivity.class);
                    startActivity(intent);
                    MyApplication.getInstance().getRepository().addUser(user);
                    MyApplication.getInstance().setUser(user);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this, "ошибка связи!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<User>call, Throwable t) {
                Toast.makeText(LoginActivity.this, "ошибка связи!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
