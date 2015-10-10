package com.pseudocode.infovents.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pseudocode.infovents.Fragments.FinishedEventFragment;
import com.pseudocode.infovents.Fragments.OngoingEventFragment;
import com.pseudocode.infovents.Fragments.UpcommingEventFragment;

/**
 * Created by Baymax on 10/10/2015.
 */
public class EventViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public EventViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            UpcommingEventFragment tab1 = new UpcommingEventFragment();
            return tab1;
        }
        else if(position == 2)
        {
            FinishedEventFragment tab2 = new FinishedEventFragment();
            return tab2;
        }

        else{
            OngoingEventFragment tab3 = new OngoingEventFragment();
            return tab3;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
