package com.example.user.bakingapp.AppWidget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.user.bakingapp.R;

/**
 * Created by Mina on 20/11/2017.
 */

public class BakingWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            RemoteViews mView = initViews(context, appWidgetManager, widgetId);
            appWidgetManager.updateAppWidget(widgetId, mView);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.baking_widget_provider_layout);

        Intent intent = new Intent(context, RemoteService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        mView.setRemoteAdapter(widgetId, R.id.widgetCollectionList, intent);
//notify widget
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
//                new ComponentName(context, BakingWidgetProvider.class));
//        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetCollectionList);

        return mView;
    }
}
