package com.example.user.bakingapp.AppWidget;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Mina on 20/11/2017.
 */




public class RemoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        WidgetDataProvider dataProvider = new WidgetDataProvider(
                getApplicationContext(), intent);
        return dataProvider;
    }

}