package com.example.user.bakingapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bakingapp.DetailActivity;
import com.example.user.bakingapp.Models.RecipeCard;
import com.example.user.bakingapp.R;
import com.example.user.bakingapp.Step_Detailed;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.example.user.bakingapp.Fragments.DetailFragment.s_;

/**
 * Created by Mina on 07/11/2017.
 */

public class Step_DetailFragment extends Fragment {
    private String video_link;
    private String title;
    private String Description;
    private String thumbnail;
    private int position;
    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;


    long exo_pos;
    TextView title_;
    TextView Desc;
    Button next;

    RecipeCard r;
    private ImageView thumbnail_image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_step_detail,container,false);


        if(DetailActivity.two_pane) {
            Bundle sendbundle = getArguments();
            r = sendbundle.getParcelable("step_tab");
            video_link=r.getSt_videoUrl();
            title=r.getSt_shortDescription();
            Description=r.getSt_Description();
            thumbnail=r.getSt_thumbnail();
           position=r.getPos();
           // position=getActivity().getIntent().getIntExtra("pos",0);


        }else{


           Intent intent = getActivity().getIntent();
            r = intent.getParcelableExtra("step_tab");
            video_link=r.getSt_videoUrl();
            title=r.getSt_shortDescription();
            Description=r.getSt_Description();
            thumbnail=r.getSt_thumbnail();
            position=r.getPos();

            //position=getActivity().getIntent().getIntExtra("pos",0);

        }


        simpleExoPlayerView=(SimpleExoPlayerView)view.findViewById(R.id.exo_view);




        title_=(TextView)view.findViewById(R.id.title);
        title_.setText(title);
        Desc=(TextView)view.findViewById(R.id.desc);
        Desc.setText(Description);
        if ((video_link.isEmpty())&&thumbnail.isEmpty()) {

            simpleExoPlayerView.setVisibility(View.GONE);
        }else if (video_link.isEmpty()&&!thumbnail.isEmpty()){

            initplayer(Uri.parse(thumbnail));
        }else {

            initplayer(Uri.parse(video_link));
        }

        if (!(thumbnail.isEmpty())) {
            String substr = thumbnail.substring(thumbnail.length() - 3);
            if (!(substr.equals("mp4"))) {
                thumbnail_image = (ImageView) view.findViewById(R.id.thumb_image);

                Glide.with(getContext())
                        .load(thumbnail)
                        .into(thumbnail_image);
                thumbnail_image.setVisibility(View.VISIBLE);
                simpleExoPlayerView.setVisibility(View.GONE);

            }
        }



        next=(Button)view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int last_element=s_.size()-1;
                if (position==last_element){
                    position=0;
                }else if(position<last_element){
                    position++;
                }
                s_.get(position).setPos(position);
                if (DetailActivity.two_pane){
                    Step_DetailFragment df=new Step_DetailFragment();
                    Bundle Extras=new Bundle();
                    Extras.putParcelable("step_tab",s_.get(position));
                    df.setArguments(Extras);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Step_Fragment_,df,"").commit();

                }else {
                    Intent intent = new Intent(view.getContext(), Step_Detailed.class);

                    intent.putExtra("step_tab", s_.get(position));

                    getActivity().startActivity(intent);
                }
            }
        });

        if (savedInstanceState!=null){

            exo_pos = savedInstanceState.getParcelable("po");
            simpleExoPlayer.seekTo(exo_pos);

        }


        return  view;
    }


    public void initplayer(Uri m){
        TrackSelector trackSelector =
                new DefaultTrackSelector();
        LoadControl loadControl=new DefaultLoadControl();
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector,loadControl);

        simpleExoPlayerView.setPlayer(simpleExoPlayer);
        String i= Util.getUserAgent(getActivity(),"videoplayer");
        MediaSource mediaSource =new ExtractorMediaSource(m,new DefaultDataSourceFactory(getActivity(),i),new DefaultExtractorsFactory(),null,null);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        exo_pos=simpleExoPlayer.getCurrentPosition();

    }

    /**
     *
     * release exo player
     */

    private void relase(){

        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
                relase();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("po",exo_pos);
    }

}
