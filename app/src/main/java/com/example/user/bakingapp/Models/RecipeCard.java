package com.example.user.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mina on 04/11/2017.
 */
public class RecipeCard implements Parcelable {
    private int id;
    private String name;
    private String ing_quantity;
    private String ing_measure;
    private String ing_ingredient;
    private int id_step;
    private String st_shortDescription;
    private String st_Description;
    private String st_videoUrl;
    private String st_thumbnail;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getIng_quantity() {
        return ing_quantity;
    }

    public void setIng_quantity(String ing_quantity) {
        this.ing_quantity = ing_quantity;
    }

    public String getIng_measure() {
        return ing_measure;
    }

    public void setIng_measure(String ing_measure) {
        this.ing_measure = ing_measure;
    }

    public String getIng_ingredient() {
        return ing_ingredient;
    }

    public void setIng_ingredient(String ing_ingredient) {
        this.ing_ingredient = ing_ingredient;
    }

    public int getId_step() {
        return id_step;
    }

    public void setId_step(int id_step) {
        this.id_step = id_step;
    }

    public String getSt_shortDescription() {
        return st_shortDescription;
    }

    public void setSt_shortDescription(String st_shortDescription) {
        this.st_shortDescription = st_shortDescription;
    }

    public String getSt_Description() {
        return st_Description;
    }

    public void setSt_Description(String st_Description) {
        this.st_Description = st_Description;
    }

    public String getSt_videoUrl() {
        return st_videoUrl;
    }

    public void setSt_videoUrl(String st_videoUrl) {
        this.st_videoUrl = st_videoUrl;
    }

    public String getSt_thumbnail() {
        return st_thumbnail;
    }

    public void setSt_thumbnail(String st_thumbnail) {
        this.st_thumbnail = st_thumbnail;
    }

    public RecipeCard(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected RecipeCard(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ing_quantity = in.readString();
        ing_measure = in.readString();
        ing_ingredient = in.readString();
        id_step = in.readInt();
        st_shortDescription = in.readString();
        st_Description = in.readString();
        st_videoUrl = in.readString();
        st_thumbnail = in.readString();
        pos=in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(ing_quantity);
        dest.writeString(ing_measure);
        dest.writeString(ing_ingredient);
        dest.writeInt(id_step);
        dest.writeString(st_shortDescription);
        dest.writeString(st_Description);
        dest.writeString(st_videoUrl);
        dest.writeString(st_thumbnail);
        dest.writeInt(pos);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeCard> CREATOR = new Parcelable.Creator<RecipeCard>() {
        @Override
        public RecipeCard createFromParcel(Parcel in) {
            return new RecipeCard(in);
        }

        @Override
        public RecipeCard[] newArray(int size) {
            return new RecipeCard[size];
        }
    };
}