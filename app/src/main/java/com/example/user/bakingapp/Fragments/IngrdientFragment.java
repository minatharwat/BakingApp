package com.example.user.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.NetworkThreads.Volley;
import com.example.user.bakingapp.R;
import com.example.user.bakingapp.Utilites.JasonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 07/11/2017.
 */

public class IngrdientFragment extends Fragment implements Volley.API {
    TextView textView;
    protected int flag;
    List<RecipeCard> ing;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ingredient,container,false);

        Volley volley=new Volley(getActivity(),this);
        volley.getapi(getString(R.string.base_url));
        flag=getActivity().getIntent().getIntExtra("position",0);

        textView=(TextView)view.findViewById(R.id.ingredit_text);

        return view;
    }

    @Override
    public void ConnectionDone(String msg) {
        ing=new ArrayList<>();
        ing.clear();

        ing= JasonParser.parseingrdients(msg,flag);
//        for (int i=0;i<ing.size();i++){
//            textView.setText(ing.get(i).getIng_quantity()+""+ing.get(i).getIng_measure()+" "+ing.get(i).getIng_ingredient()+"/n");
//        }
        StringBuilder builder = new StringBuilder();


        for (int i=0;i<ing.size();i++) {
                builder.append(ing.get(i).getIng_quantity()+" "+ing.get(i).getIng_measure()+" "+ing.get(i).getIng_ingredient() + "."+"\n");
            }

            textView.setText(builder.toString());
        }

}
