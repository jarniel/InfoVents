package com.pseudocode.infovents.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pseudocode.infovents.R;

/**
 * Created by Baymax on 10/10/2015.
 */
public class JoinedOrganizationFragment  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_joined_organization, container, false);

        return v;
    }
}
