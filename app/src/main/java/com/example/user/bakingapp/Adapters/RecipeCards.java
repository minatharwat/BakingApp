package com.example.user.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bakingapp.DetailActivity;
import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.R;
import com.example.user.bakingapp.Utilites.Logs;

import java.util.List;

/**
 * Created by Mina on 04/11/2017.
 */

public class RecipeCards extends RecyclerView.Adapter<RecipeCards.viewHolder> {
    List<RecipeCard> m;
    private Context mcontext;
    public RecipeCard obj;
    public static int pos;
    public static int x;

    public RecipeCards(List<RecipeCard> m, Context mcontext) {
        this.m=m;
        this.mcontext=mcontext;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        viewHolder viewHolder = new viewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        obj=m.get(position);
        holder.name.setText(obj.getName());


        //   holder.id.setText(String.valueOf(obj.getId()));
        // holder.description.setText(String.valueOf(obj.description));
       // holder.end_date.setText(String.valueOf(obj.end_date));
        //  Picasso.with(mcontext).load(mcontext.getString(R.string.base_url)+"/images/"+obj.getImage().substring(2)).error(R.drawable.s1).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return m.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public viewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name_id);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


              pos=getLayoutPosition();

            Intent intent = new Intent(v.getContext(),DetailActivity.class);
//            intent.putExtra("id",String.valueOf(s_.get(pos).getId_step()));
           //intent.putExtra("shortDesc",String.valueOf(s_.get(pos).getSt_shortDescription()));

        intent.putExtra("position",getLayoutPosition());
           // itemListener.recyclerViewListClicked(v, this.getLayoutPosition());

            Logs.toast(v.getContext(),""+getLayoutPosition());

            //intent.putParcelableArrayListExtra("stepslist", (ArrayList<? extends Parcelable>) s_);

            itemView.getContext().startActivity(intent);





        }
    }





}

