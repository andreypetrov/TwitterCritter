package com.petrovdevelopment.twittercritter.ui.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petrovdevelopment.twittercritter.R;
import com.petrovdevelopment.twittercritter.model.Page;
import com.petrovdevelopment.twittercritter.ui.main.MainPresenter;

/**
 * Created by Andrey on 2018-02-17.
 * Coding is fun!
 */

public class PageFragment extends Fragment {
    public static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";
    private MainPresenter presenter;
    private int sectionNumber; //TODO push state in presenter?

    private TextView sectionLabel;



    public static PageFragment newInstance(int sectionNumber) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_SECTION_NUMBER, sectionNumber);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        sectionLabel = view.findViewById(R.id.section_label);
        updateView();
        return view;
    }

    //TODO push view update in presenter
    public void updateView() {
        sectionLabel.setText("Welcome to section number " + sectionNumber + " " + presenter.getPage(sectionNumber).title);
    }
}
