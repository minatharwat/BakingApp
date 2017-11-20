package com.example.user.bakingapp.AppWidget;


import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.user.bakingapp.IngredientActivity;
import com.example.user.bakingapp.R;

/**
 * Created by Mina on 18/11/2017.
 */


public class UpdateWidget_service extends Service {


    @Override
    public void onStart(Intent intent, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);


        for (int widgetId : allWidgetIds) {

            CharSequence widgetText = getApplicationContext().getString(R.string.appwidget_text);

            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.baking_widget);
            // Set the text
            remoteViews.setTextViewText(R.id.appwidget_text,
                    widgetText);

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(),
                    BakingWidget.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);

            clickIntent.putExtra("position", PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getInt("lolo", 2));

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);


            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

            Intent intentq=new Intent(getApplicationContext(),IngredientActivity.class);
            int x =PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("lolo", 2);
            intentq.putExtra("position",x );
            PendingIntent pendingIntento=PendingIntent.getActivity(getApplicationContext(),0,intentq,0);


            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntento);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}