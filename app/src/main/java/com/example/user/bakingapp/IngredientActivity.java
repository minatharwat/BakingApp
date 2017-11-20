package com.example.user.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.bakingapp.Fragments.IngrdientFragment;

public class IngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        IngrdientFragment fragment=new IngrdientFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmet_ingrd,fragment).commit();





    }
}
