package kondratkov.bookingofmeetingrooms.view.navigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.view.bookinghistory.BookingHistory;
import kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;
import kondratkov.bookingofmeetingrooms.view.profile.ProfileActivity;

/**
 * Created by kondratkov on 01.11.2017.
 */

public class NavigationViewMyApp implements NavigationView.OnNavigationItemSelectedListener {

    AppCompatActivity mAppCompatActivity;

    public NavigationViewMyApp(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
        NavigationView navigationView = (NavigationView) mAppCompatActivity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
        NavigationView navigationView = (NavigationView) mAppCompatActivity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(mAppCompatActivity, ProfileActivity.class);
            mAppCompatActivity.startActivity(intent);
            mAppCompatActivity.finish();
        } else if (id == R.id.nav_list) {
            Intent intent = new Intent(mAppCompatActivity, ListRoomsActivity.class);
            mAppCompatActivity.startActivity(intent);
            mAppCompatActivity.finish();
        } else if (id == R.id.nav_history) {
           Intent intent = new Intent(mAppCompatActivity, BookingHistory.class);
            mAppCompatActivity.startActivity(intent);
            mAppCompatActivity.finish();
        }else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) mAppCompatActivity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
