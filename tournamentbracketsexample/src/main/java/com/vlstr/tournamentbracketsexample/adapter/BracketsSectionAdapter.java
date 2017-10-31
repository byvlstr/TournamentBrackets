package com.vlstr.tournamentbracketsexample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vlstr.tournamentbracketsexample.Fragment.BracketsColumnFragment;
import com.vlstr.tournamentbracketsexample.model.ColumnData;

import java.util.ArrayList;


/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class BracketsSectionAdapter  extends FragmentStatePagerAdapter {

    private ArrayList<ColumnData> sectionList;


    public BracketsSectionAdapter(FragmentManager fm, ArrayList<ColumnData> sectionList) {
        super(fm);
        this.sectionList =sectionList;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("colomn_data", this.sectionList.get(position));
        BracketsColumnFragment fragment = new BracketsColumnFragment();
        bundle.putInt("section_number", position);
        if (position > 0)
            bundle.putInt("previous_section_size", sectionList.get(position - 1).getMatches().size());
        else if (position == 0)
            bundle.putInt("previous_section_size", sectionList.get(position).getMatches().size());
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return this.sectionList.size();
    }
}
