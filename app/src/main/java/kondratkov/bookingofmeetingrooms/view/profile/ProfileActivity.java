package kondratkov.bookingofmeetingrooms.view.profile;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kondratkov.bookingofmeetingrooms.MyApplication;
import kondratkov.bookingofmeetingrooms.R;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.textViewProfileName)TextView textViewProfileName;
    @BindView(R.id.textViewProfilePassword)TextView textViewProfilePassword;
    @BindView(R.id.editTextProfileAddress)EditText editTextProfileAddress;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        MyApplication.getInstance().getNavigationViewMyApp().setAppCompatActivity(ProfileActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Профиль");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                textViewProfileName.setText(MyApplication.getInstance().getRepository().getUser().getUserName());
                textViewProfileName.setText(MyApplication.getInstance().getRepository().getUser().getPassword());
            }
        });
    }

    @OnClick(R.id.buttonProfileAddress)
    public void onClick33(View view){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
