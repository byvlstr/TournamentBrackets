package com.vlstr.tournamentbracketsexample.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlstr.tournamentbracketsexample.R;
import com.vlstr.tournamentbracketsexample.adapter.BracketsCellAdapter;
import com.vlstr.tournamentbracketsexample.model.ColumnData;
import com.vlstr.tournamentbracketsexample.model.MatchData;
import com.vlstr.tournamentbracketsexample.utils.UiUtils;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class BracketsColumnFragment extends Fragment {

    private ColumnData columnData;
    private int sectionNumber = 0;
    private int previousBracketSize;
    private ArrayList<MatchData> list;
    private RecyclerView bracketsRV;

    private BracketsCellAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brackets_colomn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        getExtras();
        initAdapter();
    }

    private void initViews() {

        bracketsRV = (RecyclerView) getView().findViewById(R.id.rv_score_board);
    }

    public ArrayList<MatchData> getColomnList() {
        return list;
    }

    private void getExtras() {
        if (getArguments() != null) {
            list = new ArrayList<>();
            columnData = (ColumnData) getArguments().getSerializable("colomn_data");
            sectionNumber = getArguments().getInt("section_number");
            previousBracketSize = getArguments().getInt("previous_section_size");
            list.addAll(columnData.getMatches());
            setInitialHeightForList();
        }
    }
    public int getSectionNumber() {
        return sectionNumber;
    }

    private void setInitialHeightForList() {
        for (MatchData data : list){
            if (sectionNumber == 0){
                data.setHeight(UiUtils.dpToPx(getContext(), 131));
            }else if (sectionNumber == 1 && previousBracketSize != list.size()){
                data.setHeight(UiUtils.dpToPx(getContext(), 262));
            }else if (sectionNumber == 1 && previousBracketSize == list.size()) {
                data.setHeight(UiUtils.dpToPx(getContext(), 131));
            } else if (previousBracketSize > list.size()) {
                data.setHeight(UiUtils.dpToPx(getContext(), 262));
            }else if (previousBracketSize == list.size()) {
                data.setHeight(UiUtils.dpToPx(getContext(), 131));
            }
        }

    }

    public void expandHeight(int height) {

        for (MatchData data : list) {
            data.setHeight(height);
        }
        adapter.setList(list);
    }

    public void shrinkView(int height) {
        for (MatchData data : list) {
            data.setHeight(height);
        }
        adapter.setList(list);
    }

    private void initAdapter() {
        adapter = new BracketsCellAdapter(this, getContext(), list);
        if (bracketsRV != null) {
            bracketsRV.setHasFixedSize(true);
            bracketsRV.setNestedScrollingEnabled(false);
            bracketsRV.setAdapter(adapter);
            bracketsRV.smoothScrollToPosition(0);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            bracketsRV.setLayoutManager(layoutManager);
            bracketsRV.setItemAnimator(new DefaultItemAnimator());
        }
    }

    public int getCurrentBracketSize() {
        return list.size();
    }
    public int getPreviousBracketSize() {
        return previousBracketSize;
    }
}
