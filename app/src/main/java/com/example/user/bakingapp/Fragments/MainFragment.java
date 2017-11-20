package com.example.user.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bakingapp.Adapters.RecipeCards;
import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.NetworkThreads.Volley;
import com.example.user.bakingapp.R;
import com.example.user.bakingapp.Utilites.JasonParser;
import com.example.user.bakingapp.Utilites.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 04/11/2017.
 */

public class MainFragment extends Fragment implements Volley.API  {





    RecipeCards adabter;
    RecyclerView recyclerView;
    public static List<RecipeCard> p;
    int I;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         view =inflater.inflate(R.layout.fragment_main,container,false);




            if (savedInstanceState == null) {
                Volley volley = new Volley(getActivity(), this);
                volley.getapi(getString(R.string.base_url));

            }

            return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void ConnectionDone(String msg) {
        Logs.log("1"+msg);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycle_reciprs_cards);

        p = new ArrayList<RecipeCard>();
        p.clear();



       RecipeCard w=new RecipeCard();
        p = JasonParser.parseCards(msg);

        //Logs.toast(getContext(),""+get_po());


        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }



        adabter=new RecipeCards(p,getActivity());
        recyclerView.setAdapter(adabter);

    }



}
