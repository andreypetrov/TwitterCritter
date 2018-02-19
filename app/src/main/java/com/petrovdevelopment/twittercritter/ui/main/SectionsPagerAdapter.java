package com.petrovdevelopment.twittercritter.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.petrovdevelopment.twittercritter.ui.page.PageFragment;

/**
 * Created by Andrey on 2018-02-17.
 * Coding is fun!
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    MainPresenter presenter;

    public SectionsPagerAdapter(FragmentManager fm, MainPresenter presenter) {
        super(fm);
        this.presenter = presenter;
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment pageFragment = PageFragment.newInstance(position);
        pageFragment.setPresenter(presenter);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return presenter.getCount();
    }
}
