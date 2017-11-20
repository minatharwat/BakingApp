package com.example.user.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.bakingapp.Fragments.DetailFragment;
import com.example.user.bakingapp.Fragments.Step_DetailFragment;
import com.example.user.bakingapp.Models.RecipeCard;

public class DetailActivity extends AppCompatActivity implements NameListener{


    DetailFragment detailFragment;
    public static boolean two_pane =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



         detailFragment=new DetailFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.Fragment_detail,detailFragment).commit();
        detailFragment.setNameListener(this);


        if(null!=findViewById(R.id.Step_Fragment_)){

            two_pane=true;
        }



    }

    @Override
    public void set_the_name(RecipeCard o) {
        //case 1 pane
        if(!two_pane) {

            Intent intent = new Intent(this, Step_Detailed.class);
            intent.putExtra("step_tab", o);
            startActivity(intent);


        }else{
            //make the case 2pane
            Step_DetailFragment df=new Step_DetailFragment();
            Bundle Extras=new Bundle();
            Extras.putParcelable("step_tab",o);
            df.setArguments(Extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.Step_Fragment_,df,"").commit();

        }

    }





}
