package com.example.hotelmoileapplication.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmoileapplication.data.local.UserSharedPreference;

public class BaseActivity extends AppCompatActivity  {

    @Override
    protected void onResume() {
        super.onResume();
        if (false == UserSharedPreference.checkLogin(this)){
            Intent intent = new Intent(this,LoginActivity2.class);
            startActivity(intent);
            finish();
        }
    }
}
