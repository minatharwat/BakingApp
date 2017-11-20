package com.example.user.bakingapp.Utilites;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.user.bakingapp.Models.RecipeCard;

import java.util.ArrayList;

/**
 * Created by ${Mina} on 04/11/2017.
 */

public class Logs {
    public static ArrayList<RecipeCard> List = new ArrayList<RecipeCard>();

    public static void log(String s){

        Log.e("tag",s);
    }
    public static void toast(Context context,String s){

        Toast.makeText(context, s, Toast.LENGTH_LONG).show();

    }
}
