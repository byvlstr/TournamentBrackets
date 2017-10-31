package com.vlstr.tournamentbracketsexample.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.vlstr.tournamentbracketsexample.Fragment.BracketsColumnFragment;
import com.vlstr.tournamentbracketsexample.R;
import com.vlstr.tournamentbracketsexample.model.MatchData;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class BracketsCellAdapter extends RecyclerView.Adapter<BracketsCellViewHolder>{

    private BracketsColumnFragment fragment;
    private Context context;
    private ArrayList<MatchData> list;

    public BracketsCellAdapter(BracketsColumnFragment bracketsColumnFragment, Context context, ArrayList<MatchData> list) {

        this.fragment = bracketsColumnFragment;
        this.context = context;
        this.list = list;
    }

    @Override
    public BracketsCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cell_brackets, parent, false);
        return new BracketsCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BracketsCellViewHolder holder, int position) {
        setFields(holder, position);
    }

    private void setFields(final BracketsCellViewHolder viewHolder, final int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHolder.setAnimation(list.get(position).getHeight());
            }
        }, 100);

        viewHolder.getTeamOneName().setText(list.get(position).getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(list.get(position).getCompetitorTwo().getName());
        viewHolder.getHomeTeamScore().setText(list.get(position).getCompetitorOne().getScore());
        viewHolder.getAwayTeamScore().setText(list.get(position).getCompetitorTwo().getScore());

        initScoreListeners(viewHolder);
    }

    private void initScoreListeners(BracketsCellViewHolder viewHolder) {
        final EditText homeTeamScoreView = viewHolder.getHomeTeamScore();
        final EditText awayTeamScoreView = viewHolder.getAwayTeamScore();

        homeTeamScoreView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Integer homeTeamScore = Integer.valueOf(s.toString());
                Integer awayTeamScore = Integer.valueOf(awayTeamScoreView.getText().toString());
                if (homeTeamScore != null && awayTeamScore != null) {
                    // TODO move winning team name to next Cell
                }
            }
        });

        awayTeamScoreView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Integer awayTeamScore = Integer.valueOf(s.toString());
                Integer homeTeamScore = Integer.valueOf(homeTeamScoreView.getText().toString());
                if (awayTeamScore != null && homeTeamScore != null) {
                    // TODO move winning team name to next Cell
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void setList(ArrayList<MatchData> colomnList) {
        this.list = colomnList;
        notifyDataSetChanged();
    }
}
