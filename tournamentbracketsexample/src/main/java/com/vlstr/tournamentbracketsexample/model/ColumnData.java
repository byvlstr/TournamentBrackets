package com.vlstr.tournamentbracketsexample.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class ColumnData implements Serializable {

    private ArrayList<MatchData> matches;

    public ColumnData(ArrayList<MatchData> matches) {
        this.matches = matches;
    }

    public void setMatches(ArrayList<MatchData> matches) {
        this.matches = matches;
    }

    public ArrayList<MatchData> getMatches() {
        return matches;
    }
}
