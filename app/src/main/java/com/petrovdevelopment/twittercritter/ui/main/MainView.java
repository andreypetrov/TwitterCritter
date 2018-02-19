package com.petrovdevelopment.twittercritter.ui.main;

/**
 * Created by Andrey on 2018-02-17.
 * Coding is fun!
 */

public interface MainView {
    void goToPage(int pageIndex);
    void showToast(String toastMessage);
    void updateView();
}
