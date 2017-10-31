package com.vlstr.tournamentbracketsexample.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vlstr.tournamentbracketsexample.R;
import com.vlstr.tournamentbracketsexample.adapter.BracketsSectionAdapter;
import com.vlstr.tournamentbracketsexample.customviews.TournamentViewPager;
import com.vlstr.tournamentbracketsexample.model.ColumnData;
import com.vlstr.tournamentbracketsexample.model.CompetitorData;
import com.vlstr.tournamentbracketsexample.model.MatchData;
import com.vlstr.tournamentbracketsexample.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private TournamentViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColumnData> tournamentRoundsDataList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;

    public static void init(AppCompatActivity activity, @IdRes int containerId, BracketsFragment bracketFragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerId, bracketFragment, "brackets_home_fragment");
        transaction.commit();
        manager.executePendingTransactions();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brackts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        intialiseViewPagerAdapter();
    }

    public void setData(@NonNull ArrayList<ColumnData> tournamentRoundsDataList) {
        this.tournamentRoundsDataList = tournamentRoundsDataList;
    }

    private void intialiseViewPagerAdapter() {

        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(),this.tournamentRoundsDataList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);

        viewPager.addOnPageChangeListener(this);
    }

    private void initViews() {

        viewPager = getView().findViewById(R.id.tournament_view_pager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            // We are moving to next screen on right side
            if (positionOffset > 0.5) {
                // Closer to next screen than to current
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view here
                    if (getBracketsFragment(position).getColomnList().get(0).getHeight()
                            != UiUtils.dpToPx(getContext(), 131))
                        getBracketsFragment(position).shrinkView(UiUtils.dpToPx(getContext(), 131));
                    if (getBracketsFragment(position + 1).getColomnList().get(0).getHeight()
                            != UiUtils.dpToPx(getContext(), 131))
                        getBracketsFragment(position + 1).shrinkView(UiUtils.dpToPx(getContext(), 131));
                }
            } else {
                // Closer to current screen than to next
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateViewhere

                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                            getBracketsFragment(position + 1).getPreviousBracketSize()) {
                        getBracketsFragment(position + 1).shrinkView(UiUtils.dpToPx(getContext(), 131));
                        getBracketsFragment(position).shrinkView(UiUtils.dpToPx(getContext(), 131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousFragmentSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousFragmentSize) {
                            getBracketsFragment(position + 1).expandHeight(UiUtils.dpToPx(getContext(), 262));
                            getBracketsFragment(position).shrinkView(UiUtils.dpToPx(getContext(), 131));
                        }
                    }
                }
            }
        } else {
            // We are moving to next screen left side
            if (positionOffset > 0.5) {
                // Closer to current screen than to next
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view for screen

                }
            } else {
                // Closer to next screen than to current
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateviewfor screem
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public BracketsColumnFragment getBracketsFragment(int position) {
        BracketsColumnFragment bracktsFrgmnt = null;
        if (getChildFragmentManager() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketsColumnFragment) {
                        bracktsFrgmnt = (BracketsColumnFragment) fragment;
                        if (bracktsFrgmnt.getSectionNumber() == position)
                            break;
                    }
                }
            }
        }
        return bracktsFrgmnt;
    }
}
