package com.example.user.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.bakingapp.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MainFragment mainFragment=new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.Fragment_Main,mainFragment).commit();
    }

}
