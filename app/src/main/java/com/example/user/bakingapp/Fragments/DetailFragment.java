package com.example.user.bakingapp.Fragments;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bakingapp.Adapters.RecipeSteps;
import com.example.user.bakingapp.AppWidget.BakingWidgetProvider;
import com.example.user.bakingapp.IngredientActivity;
import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.NameListener;
import com.example.user.bakingapp.NetworkThreads.Volley;
import com.example.user.bakingapp.R;
import com.example.user.bakingapp.Utilites.JasonParser;
import com.example.user.bakingapp.Utilites.Logs;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.bakingapp.Fragments.MainFragment.ingredient_for_widget;



/**
 * Created by Mina on 06/11/2017.
 */

public class DetailFragment extends Fragment implements Volley.API {
    public static NameListener nameListenero;
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    private int lastFirstVisiblePosition;

    public static void setNameListener(NameListener nameListener){
        nameListenero=nameListener;
    }

    //List<RecipeCard> r;
    View view;
    public RecyclerView recyclerView;
    protected RecipeSteps adapter;
    public static List<RecipeCard> s_;
    public static int za=-1;

    TextView ingredit_click_text;





    int flag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_detail,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_reciprs_steps);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        flag=getActivity().getIntent().getIntExtra("position",0);
     //  Logs.log(" "+r);

        if (savedInstanceState!=null) {
            lastFirstVisiblePosition = savedInstanceState.getInt("q");
        }

       if (savedInstanceState==null) {
           Volley volley = new Volley(getActivity(), this);
           volley.getapi(getString(R.string.base_url));



       }

        ingredit_click_text=(TextView)view.findViewById(R.id.g);

        ingredit_click_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(),IngredientActivity.class);
                intent2.putExtra("position",flag);
                startActivity(intent2);
            }
        });

        //send to the widget the id of last visited recipe
//        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
//        edit.putInt("lolo",flag);
//        edit.apply();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("saved", flag);
        editor.apply();

        return view;
    }

    @Override
    public void ConnectionDone(String msg) {


        s_=new ArrayList<RecipeCard>();
        s_.clear();
        s_ = JasonParser.parseSteps(msg,flag);

     set_controls(s_);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int retrievedValue = sp.getInt("saved" , 0);

        ingredient_for_widget=JasonParser.parseingrdients(msg,retrievedValue);


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
                new ComponentName(getActivity(), BakingWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetCollectionList);


    }

    public void set_controls(List<RecipeCard> r){



        adapter = new RecipeSteps(r, getActivity());
recyclerView.scrollToPosition(za);
        recyclerView.setAdapter(adapter);

    }





    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            ingredit_click_text.setVisibility(View.GONE);
            lastFirstVisiblePosition=savedInstanceState.getInt("q");
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(lastFirstVisiblePosition,0);
           // ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
          //  recyclerView.scrollToPosition(4);
            s_.clear();
            s_=savedInstanceState.getParcelableArrayList("list");
            Logs.log("onRestttore ");
            Logs.log("onRestttore==== "+lastFirstVisiblePosition);

           za=lastFirstVisiblePosition;
            adapter = new RecipeSteps(s_, getActivity());
            recyclerView.scrollToPosition(lastFirstVisiblePosition);
            recyclerView.setAdapter(adapter);



        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        int Position= ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        outState.putInt("q",Position);
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) s_);

        Logs.log("onsave ");
    }


}
