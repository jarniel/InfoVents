package com.pseudocode.infovents.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.pseudocode.infovents.Adapters.EventAdapter;
import com.pseudocode.infovents.Classes.Event;
import com.pseudocode.infovents.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Baymax on 10/10/2015.
 */
public class FinishedEventFragment extends Fragment {
    RecyclerView mRecyclerView;
    Firebase mFirebase;
    EventAdapter mAdapter;
    //ProgressBar progressBar;
    Date date;
    Long time;
    Long te;
    Long ts;
    Date ds;
    Date de;
    private List<Event> mEvents = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_finished_events, container, false);



        mRecyclerView = (RecyclerView) v.findViewById(R.id.finished_event_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EventAdapter(getActivity(), mEvents);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
       // progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        Firebase.setAndroidContext(getActivity());
        mFirebase = new Firebase(getResources().getString(R.string.firebaseRef)).child("events");
        Query upcomingEventsQuery = mFirebase.orderByChild("endDate");

        upcomingEventsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                date = new Date();
                time = date.getTime();

                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                   // progressBar.setVisibility(View.GONE);
                    Event model = dataSnapshot.getValue(Event.class);
                    try {
                        de = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getEndDate().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    te = de.getTime();

                    if (te < time) {

                        model.setEventId(dataSnapshot.getKey());
                        mEvents.add(model);
                        mRecyclerView.scrollToPosition(mEvents.size() - 1);
                        mAdapter.notifyItemInserted(mEvents.size() - 1);


                    }


                }

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return v;

    }

}

