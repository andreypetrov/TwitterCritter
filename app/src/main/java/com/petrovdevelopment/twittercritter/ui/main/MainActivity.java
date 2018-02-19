package com.petrovdevelopment.twittercritter.ui.main;

import android.app.Presentation;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.petrovdevelopment.twittercritter.R;
import com.petrovdevelopment.twittercritter.ui.page.PageFragment;

public class MainActivity extends AppCompatActivity implements MainView{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FloatingActionButton fab;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        mViewPager = findViewById(R.id.container);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        presenter = new MainPresenter(this);
        initUi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewLoaded();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewLoaded();
    }

    private void initUi() {
        initFloatingActionButton();
        initViewPager();
    }

    public void initViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), presenter);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void initFloatingActionButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFloatingButtonClicked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void goToPage(int pageIndex) {

    }

    @Override
    public void updateView() {
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String toastMessage) {
        Snackbar.make(fab, toastMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
