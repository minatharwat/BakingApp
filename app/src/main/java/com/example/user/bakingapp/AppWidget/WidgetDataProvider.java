package com.example.user.bakingapp.AppWidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.user.bakingapp.Models.RecipeCard;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.bakingapp.Fragments.MainFragment.ingredient_for_widget;


/**
 * Created by Mina on 20/11/2017.
 */
@SuppressLint("NewApi")
public class WidgetDataProvider implements RemoteViewsFactory {

        List<RecipeCard> m = new ArrayList();


        Context mContext = null;

        public WidgetDataProvider(Context context, Intent intent) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return m.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                    android.R.layout.simple_list_item_1);

            mView.setTextViewText(android.R.id.text1, m.get(position).getIng_quantity()+m.get(position).getIng_measure()+m.get(position).getIng_ingredient());
            mView.setTextColor(android.R.id.text1, Color.BLACK);
            return mView;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onCreate() {
            initializeData();
        }

        @Override
        public void onDataSetChanged() {
            initializeData();
        }

        private void initializeData() {
            //ingredient_for_widget.clear();
            m.clear();

                m.addAll(ingredient_for_widget);


        }

        @Override
        public void onDestroy() {

        }
}
