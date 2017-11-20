package com.example.user.bakingapp.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {


    private static final String LOG = "com.example.user.bakingapp.AppWidget";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

      //  CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
   //     RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);

    //    views.setTextViewText(R.id.appwidget_text, widgetText);

      //  Intent intent=new Intent(context,IngredientActivity.class);
        //intent.putExtra("position", PreferenceManager.getDefaultSharedPreferences(context).getInt("kaka", 2));
        //PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
       // views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);



       // appWidgetManager.updateAppWidget(appWidgetId, views);

        ComponentName thisWidget = new ComponentName(context,
                BakingWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
                UpdateWidget_service.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);


        context.startService(intent);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }



        ComponentName thisWidget = new ComponentName(context,
                BakingWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
                UpdateWidget_service.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

        // Update the widgets using service
        context.startService(intent);



    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }






}

