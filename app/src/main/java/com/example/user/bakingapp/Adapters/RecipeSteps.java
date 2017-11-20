package com.example.user.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.R;

import java.util.List;

import static com.example.user.bakingapp.Fragments.DetailFragment.nameListenero;
import static com.example.user.bakingapp.Fragments.DetailFragment.s_;

/**
 * Created by Mina on 04/11/2017.
 */

public class RecipeSteps extends RecyclerView.Adapter<RecipeSteps.viewHolder> {

    List<RecipeCard> m;
    private Context mcontext;
    public RecipeCard obj;
    public RecipeSteps(List<RecipeCard> m, Context mcontext) {
        this.m=m;
        this.mcontext=mcontext;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_step, parent, false);
        viewHolder viewHolder = new viewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        obj=m.get(position);

            holder.short_desc.setText(obj.getSt_shortDescription());


    }

    @Override
    public int getItemCount() {
        return m.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView short_desc;

        public viewHolder(View itemView) {
            super(itemView);
            short_desc=(TextView)itemView.findViewById(R.id.name_id_s);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int pos=getLayoutPosition();
       /*
            intent.putExtra("video",String.valueOf(s_.get(pos).getSt_videoUrl()));
            intent.putExtra("shortDesc",s_.get(pos).getSt_shortDescription());
            intent.putExtra("description",s_.get(pos).getSt_Description());
            intent.putExtra("thumbnail",s_.get(pos).getSt_thumbnail());
*/
            //  Intent intent = new Intent(v.getContext(),Step_Detailed.class);
            // intent.putExtra("pos",pos);
            //itemView.getContext().startActivity(intent);

           s_.get(pos).setPos(getLayoutPosition());


            RecipeCard recipeCard=s_.get(pos);


            nameListenero.set_the_name(recipeCard);



        }
    }
}

