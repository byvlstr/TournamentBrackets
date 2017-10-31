package com.vlstr.tournamentbracketsexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vlstr.tournamentbracketsexample.Fragment.BracketsFragment;
import com.vlstr.tournamentbracketsexample.R;
import com.vlstr.tournamentbracketsexample.model.ColumnData;
import com.vlstr.tournamentbracketsexample.model.CompetitorData;
import com.vlstr.tournamentbracketsexample.model.MatchData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BracketsFragment bracketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseBracketsFragment();
    }

    private void initialiseBracketsFragment() {

        bracketFragment = new BracketsFragment();
        bracketFragment.setData(getDefaultData());
        BracketsFragment.init(this, R.id.container, bracketFragment);
    }

    private ArrayList<ColumnData> getDefaultData() {
        ArrayList<ColumnData> sectionList = new ArrayList<>();
        ArrayList<MatchData> Colomn1matchesList = new ArrayList<>();
        ArrayList<MatchData> colomn2MatchesList = new ArrayList<>();
        ArrayList<MatchData> colomn3MatchesList = new ArrayList<>();
        CompetitorData competitorOne = new CompetitorData("Manchester United Fc", "2");
        CompetitorData competitorTwo = new CompetitorData("Arsenal", "1");
        CompetitorData competitorThree = new CompetitorData("Chelsea", "2");
        CompetitorData competitorFour = new CompetitorData("Tottenham", "1");
        CompetitorData competitorFive = new CompetitorData("Manchester FC", "2");
        CompetitorData competitorSix = new CompetitorData("Liverpool", "4");
        CompetitorData competitorSeven = new CompetitorData("West ham ", "2");
        CompetitorData competitorEight = new CompetitorData("Bayern munich", "1");
        MatchData matchData1 = new MatchData(competitorOne,competitorTwo);
        MatchData matchData2 = new MatchData(competitorThree, competitorFour);
        MatchData matchData3 = new MatchData(competitorFive,competitorSix);
        MatchData matchData4 = new MatchData(competitorSeven, competitorEight);
        Colomn1matchesList.add(matchData1);
        Colomn1matchesList.add(matchData2);
        Colomn1matchesList.add(matchData3);
        Colomn1matchesList.add(matchData4);
        ColumnData columnData1 = new ColumnData(Colomn1matchesList);
        sectionList.add(columnData1);
        CompetitorData competitorNine = new CompetitorData("Manchester United Fc", "2");
        CompetitorData competitorTen = new CompetitorData("Chelsea", "4");
        CompetitorData competitorEleven = new CompetitorData("Liverpool", "2");
        CompetitorData competitorTwelve = new CompetitorData("westham", "1");
        MatchData matchData5 = new MatchData(competitorNine,competitorTen);
        MatchData matchData6 = new MatchData(competitorEleven, competitorTwelve);
        colomn2MatchesList.add(matchData5);
        colomn2MatchesList.add(matchData6);
        ColumnData columnData2 = new ColumnData(colomn2MatchesList);
        sectionList.add(columnData2);
        CompetitorData competitorThirteen = new CompetitorData("Chelsea", "2");
        CompetitorData competitorForteen = new CompetitorData("Liverpool", "1");
        MatchData matchData7 = new MatchData(competitorThirteen, competitorForteen);
        colomn3MatchesList.add(matchData7);
        ColumnData columnData3 = new ColumnData(colomn3MatchesList);
        sectionList.add(columnData3);

        return sectionList;
    }
}
