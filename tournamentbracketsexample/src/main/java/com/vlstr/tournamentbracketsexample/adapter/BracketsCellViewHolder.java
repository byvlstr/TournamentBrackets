package com.vlstr.tournamentbracketsexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vlstr.tournamentbracketsexample.R;
import com.vlstr.tournamentbracketsexample.animation.SlideAnimation;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellViewHolder extends RecyclerView.ViewHolder {

    private TextView teamOneName;
    private TextView teamTwoName;
    private EditText homeTeamScore;
    private EditText awayTeamScore;
    private Animation animation;
    private RelativeLayout rootLayout;

    public BracketsCellViewHolder(View itemView) {
        super(itemView);
        teamOneName = (TextView) itemView.findViewById(R.id.team_one_name);
        teamTwoName = (TextView) itemView.findViewById(R.id.team_two_name);
        homeTeamScore = (EditText) itemView.findViewById(R.id.team_one_score);
        awayTeamScore = (EditText) itemView.findViewById(R.id.team_two_score);
        rootLayout = (RelativeLayout) itemView.findViewById(R.id.layout_root);
    }

    public void setAnimation(int height){
        animation = new SlideAnimation(rootLayout, rootLayout.getHeight(),
                height);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        rootLayout.setAnimation(animation);
        rootLayout.startAnimation(animation);
    }

    public TextView getTeamTwoName() {
        return teamTwoName;
    }

    public EditText getHomeTeamScore() {
        return homeTeamScore;
    }

    public EditText getAwayTeamScore() {
        return awayTeamScore;
    }

    public TextView getTeamOneName() {
        return teamOneName;
    }
}
