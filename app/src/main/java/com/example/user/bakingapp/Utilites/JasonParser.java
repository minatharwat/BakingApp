package com.example.user.bakingapp.Utilites;

import android.content.Context;

import com.example.user.bakingapp.Models.RecipeCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mina on 04/11/2017.
 */

public class JasonParser {
    private Context mc;

    public static ArrayList<RecipeCard> parseCards(String response)
    {

    ArrayList<RecipeCard> recipeCards = new ArrayList<RecipeCard>();
//        ArrayList<RecipeCard> steps = new ArrayList<RecipeCard>();

        RecipeCard item;
        RecipeCard st_item;
    try {
        JSONArray jsonObject = new JSONArray(response);

        // JSONArray array = jsonObject.getJSONArray("data");
        JSONObject data_object;
        recipeCards.clear();
        //steps.clear();
        for (int i = 0; i < jsonObject.length(); i++) {
            data_object = jsonObject.getJSONObject(i);
            item = new RecipeCard();
            st_item=new RecipeCard();

                item.setId(data_object.getInt("id"));
                item.setName(data_object.getString("name"));
                item.setImage(data_object.getString("image"));
                recipeCards.add(item);


        }   //  FOR LOOP

    }   // TRY
    catch (JSONException e) {
        e.printStackTrace();
    } finally {
//        if (L==1){

        return recipeCards;

    }

    }

    public static ArrayList<RecipeCard> parseSteps(String response,int m)
    {
        ArrayList<RecipeCard> StepsList = new ArrayList<RecipeCard>();
        RecipeCard item;
        try
        {

            JSONArray sc = new JSONArray(response);

                JSONObject jOBJ = sc.getJSONObject(m);

                JSONArray jArray = jOBJ.getJSONArray("steps");
                for (int j = 0; j < jArray.length(); j++)
                {
                    JSONObject data_object = jArray.getJSONObject(j);

                    item = new RecipeCard();
                    item.setId(data_object.getInt("id"));
                    item.setSt_Description(data_object.getString("description"));
                    item.setSt_shortDescription(data_object.getString("shortDescription"));
                    item.setSt_videoUrl(data_object.getString("videoURL"));
                    item.setSt_thumbnail(data_object.getString("thumbnailURL"));
                    StepsList.add(item);

                }



        }   // TRY

        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return StepsList;
        }   //  FINALLY
    }

    public static ArrayList<RecipeCard> parseingrdients(String response,int m)
    {
        ArrayList<RecipeCard> gr_List = new ArrayList<RecipeCard>();
        RecipeCard item;
        try
        {

            JSONArray sc = new JSONArray(response);

            JSONObject jOBJ = sc.getJSONObject(m);

            JSONArray jArray = jOBJ.getJSONArray("ingredients");
            for (int j = 0; j < jArray.length(); j++)
            {

                JSONObject data_object = jArray.getJSONObject(j);
                item = new RecipeCard();
                item.setIng_ingredient(data_object.getString("ingredient"));
                item.setIng_measure(data_object.getString("measure"));
                item.setIng_quantity(data_object.getString("quantity"));

                gr_List.add(item);

            }


        }   // TRY

        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return gr_List;
        }   //  FINALLY
    }

}
