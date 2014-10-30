package com.google.meetupdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.Random;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private Fragment mMaterialWidgetsFragment;
    private Fragment mRevealEffectFragment;
    private Fragment mSharedElementsFragment;
    private Fragment mWebViewFragment;
    private Fragment mEnhancedNotificationsFragment;
    private Fragment mScreenCaptureFragment;
    private Fragment mScreenPinningFragment;

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = null;

        switch(position) {
            case 0:
                if (mMaterialWidgetsFragment == null)
                    mMaterialWidgetsFragment = new MaterialWidgetsFragment();

                fragment = mMaterialWidgetsFragment;
                break;
            case 1:
                if (mRevealEffectFragment == null)
                    mRevealEffectFragment = new RevealEffectFragment();

                fragment = mRevealEffectFragment;
                break;
            case 2:
                if (mSharedElementsFragment == null)
                    mSharedElementsFragment = new SharedElementsFragment();

                fragment = mSharedElementsFragment;
                break;
            case 3:
                if (mWebViewFragment == null)
                    mWebViewFragment = new WebViewFragment();

                fragment = mWebViewFragment;
                break;
            case 4:
                if (mEnhancedNotificationsFragment == null)
                    mEnhancedNotificationsFragment = new EnhancedNotificationsFragment();

                fragment = mEnhancedNotificationsFragment;
                break;
            case 5:
                if (mScreenCaptureFragment == null)
                    mScreenCaptureFragment = new ScreenCaptureFragment();

                fragment = mScreenCaptureFragment;
                break;
            case 6:
                if (mScreenPinningFragment == null)
                    mScreenPinningFragment = new ScreenPinningFragment();

                fragment = mScreenPinningFragment;
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

}
