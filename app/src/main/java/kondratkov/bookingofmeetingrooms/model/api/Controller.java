package kondratkov.bookingofmeetingrooms.model.api;

import android.content.Context;
import android.content.SharedPreferences;

import kondratkov.bookingofmeetingrooms.MyApplication;
import kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kondratkov on 07.11.2017.
 */

public class Controller {
    static final String BASE_URL = "http://46.164.250.180";
    static SharedPreferences mSharedPreferences;

    public static ApiInterface getApi()  {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        String d = retrofit.toString();

        ApiInterface umoriliApi = retrofit.create(ApiInterface.class);
        return umoriliApi;
    }

    public static ApiInterface postApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface umoriliApi = retrofit.create(ApiInterface.class);
        return umoriliApi;
    }
}
