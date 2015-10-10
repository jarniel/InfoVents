package com.pseudocode.infovents.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.pseudocode.infovents.Classes.User;
import com.pseudocode.infovents.LocalStore;
import com.pseudocode.infovents.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateOrganizationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText orgName, orgDesc, orgLocation, orgEmail, orgContact, orgLogo, orgCover;
    Spinner orgPublicity, orgIndustry;
    String mOrgName, mOrgDesc, mOrgLocation, mOrgEmail, mOrgContact, mOrgLogo, mOrgCover, mOrgPublicity, mOrgIndusrtry, mOrgMembers, mDateCreated, mOrgOwner;
    Button mBtnCreateOrg;
    Firebase mFirebaseRef;
    LocalStore mLocalstore;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_clear_24dp);

        Firebase.setAndroidContext(this);
        LocalStore mLocalstore = new LocalStore(this);

        mFirebaseRef = new Firebase(getResources().getString(R.string.firebaseRef));

        orgName = (EditText) findViewById(R.id.orgName);
        orgDesc = (EditText) findViewById(R.id.orgDesc);
        orgLocation = (EditText) findViewById(R.id.orgLocation);
        orgEmail = (EditText) findViewById(R.id.orgEmail);
        orgContact = (EditText) findViewById(R.id.orgContact);
        orgLogo = (EditText) findViewById(R.id.orgLogo);
        orgCover = (EditText) findViewById(R.id.orgCoverImage);

        orgIndustry = (Spinner) findViewById(R.id.spinner_label);
        orgPublicity = (Spinner) findViewById(R.id.spinner_label2);

        mBtnCreateOrg = (Button) findViewById(R.id.btnCreateOrg);
        mBtnCreateOrg.setOnClickListener(this);

        mUser = mLocalstore.getLoggedInUser();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateOrg:
                mOrgName = orgName.getText().toString();
                mOrgDesc = orgDesc.getText().toString();
                mOrgLocation = orgLocation.getText().toString();
                mOrgEmail = orgEmail.getText().toString();
                mOrgContact = orgContact.getText().toString();
                mOrgLogo = orgLogo.getText().toString();
                mOrgCover = orgCover.getText().toString();
                mOrgIndusrtry = orgIndustry.getSelectedItem().toString();
                mOrgPublicity = orgPublicity.getSelectedItem().toString();
                mDateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                mOrgMembers = 1 + "";
                mOrgOwner = mUser.getId();

                Firebase ref = mFirebaseRef.child("organizations");

                Map<String, String> post = new HashMap<String, String>();
                post.put("orgName", mOrgName);
                post.put("orgDesc", mOrgDesc);
                post.put("orgLocation", mOrgLocation);
                post.put("orgEmail", mOrgEmail);
                post.put("orgContact", mOrgContact);
                post.put("orgImage", mOrgLogo);
                post.put("orgCover", mOrgCover);
                post.put("orgIndustry", mOrgIndusrtry);
                post.put("orgPublicity", mOrgPublicity);
                post.put("orgOwner", mOrgOwner);
                post.put("orgMembers", mOrgMembers);
                post.put("dateCreated", mDateCreated);

                ref.push().setValue(post);
                break;
        }
    }
}
