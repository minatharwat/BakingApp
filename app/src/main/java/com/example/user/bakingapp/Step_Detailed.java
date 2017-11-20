package com.example.user.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.bakingapp.Fragments.Step_DetailFragment;

public class Step_Detailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step__detailed);


        Step_DetailFragment step_detailFragment=new Step_DetailFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.Step_Fragment_,step_detailFragment).commit();

    }



}
