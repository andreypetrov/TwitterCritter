package com.petrovdevelopment.twittercritter.ui.main;

import com.petrovdevelopment.twittercritter.model.Count;
import com.petrovdevelopment.twittercritter.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2018-02-17.
 * Coding is fun!
 */

public class MainPresenter {
    List<Page> pages;
    MainView view;
    String floatingButtonText;

    public MainPresenter(MainView view) {
        this.view = view;
        pages = new ArrayList<>();
        floatingButtonText = "";
    }


    public void onViewLoaded() {
        fetchData();
        updateView();
    }

    public void onViewUnloaded() {

    }

    private void fetchData() {
        pages = Page.createDummyPages();
        floatingButtonText = "Replace with your own action";
    }

    private void updateView() {
        view.updateView();
    }



    public int getCount() {
        return pages.size();
    }

    public Page getPage(int pageIndex) {
        return pages.get(pageIndex);
    }

    public void onFloatingButtonClicked() {
        System.out.println("onFloatingButtonClicked");
        view.showToast(floatingButtonText);
    }
}
