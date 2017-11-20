package com.example.user.bakingapp.NetworkThreads;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.bakingapp.Utilites.Logs;

/**
 * Created by Mina on 05/11/2017.
 */

public class Volley  {

    public Context mContext;
    public API apiListener;

    public Volley(Context context,API apiListener){
        this.apiListener=apiListener;
        this.mContext=context;
    }

    public void getapi(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                if(s.equals("null")){
                    Logs.log("respons is null");
                }
                else{

                   // Logs.log("response is ="+s);

                    ((API) apiListener).ConnectionDone(s.toString());

                }
               // pd.dismiss();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
              //  pd.dismiss();
            }
        });

        RequestQueue rQueue = com.android.volley.toolbox.Volley.newRequestQueue(mContext);
        rQueue.add(request);

    }



    public interface API
    {
        void ConnectionDone( String msg);
    }
}
