package com.pseudocode.infovents;

import android.content.Context;
import android.content.SharedPreferences;

import com.pseudocode.infovents.Classes.User;

/**
 * Created by Baymax on 08/10/2015.
 */
public class LocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public LocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("id", user.getId());
        userLocalDatabaseEditor.putString("fname", user.getFname());
        userLocalDatabaseEditor.putString("lname", user.getLname());
        userLocalDatabaseEditor.putString("gender", user.getGender());
        userLocalDatabaseEditor.putString("name", user.getName());
        userLocalDatabaseEditor.putString("link", user.getLink());
        userLocalDatabaseEditor.putString("avatar", user.getAvatar());
        userLocalDatabaseEditor.putString("email", user.getEmail());
        userLocalDatabaseEditor.putString("prov", user.getProv());
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        } else {

            String id = userLocalDatabase.getString("id", "");
            String fname = userLocalDatabase.getString("fname", "");
            String lname = userLocalDatabase.getString("lname", "");
            String name = userLocalDatabase.getString("name", "");
            String gender = userLocalDatabase.getString("gender", "");
            String link = userLocalDatabase.getString("link", "");
            String prov = userLocalDatabase.getString("prov", "");
            String avatar =userLocalDatabase.getString("avatar", "");
            String email = userLocalDatabase.getString("email", "");



            User user = new User(id, fname, lname, name, gender, link, prov, avatar, email);
            return user;
        }
    }
}