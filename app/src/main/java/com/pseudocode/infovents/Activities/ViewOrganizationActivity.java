package com.pseudocode.infovents.Activities;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.pseudocode.infovents.Adapters.NewsAdapter;
import com.pseudocode.infovents.Classes.News;
import com.pseudocode.infovents.Classes.User;
import com.pseudocode.infovents.Classes.WrappingLinearLayoutManager;
import com.pseudocode.infovents.LocalStore;
import com.pseudocode.infovents.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewOrganizationActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Firebase mFirebase;
    LocalStore mLocalstore;
    CircleImageView imageView;
    TextView username, useremail;
    User user;
    RecyclerView mRecyclerView;
    NewsAdapter mAdapter;
    private List<News> mNews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_organization);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Firebase.setAndroidContext(this);
        mFirebase = new Firebase(getResources().getString(R.string.firebaseRef));

        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new WrappingLinearLayoutManager(ViewOrganizationActivity.this));
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(false);

        mAdapter = new NewsAdapter(ViewOrganizationActivity.this, mNews);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Firebase.setAndroidContext(ViewOrganizationActivity.this);
        mFirebase = new Firebase(getResources().getString(R.string.firebaseRef)).child("news");
        Query newsQuery = mFirebase.limitToFirst(3);
        newsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    News model = dataSnapshot.getValue(News.class);
                    model.setNewsId(dataSnapshot.getKey());
                    mNews.add(model);
//                    mRecyclerView.scrollToPosition(mNews.size() - 1);
//                    mAdapter.notifyItemInserted(mNews.size() - 1);


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



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ViewOrganizationActivity.this);
                dialog.setContentView(R.layout.dialog_add);
                dialog.setTitle("Create");
                dialog.show();
            }
        });
    }

}
