package com.vlstr.tournamentbracketsexample.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vlstr.tournamentbracketsexample.Fragment.BracketsFragment;
import com.vlstr.tournamentbracketsexample.R;

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
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, bracketFragment, "brackets_home_fragment");
        transaction.commit();
        manager.executePendingTransactions();
    }
}
