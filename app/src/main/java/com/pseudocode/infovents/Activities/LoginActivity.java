package com.pseudocode.infovents.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.FacebookSdk;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.pseudocode.infovents.Classes.User;
import com.pseudocode.infovents.LocalStore;
import com.pseudocode.infovents.MainActivity;
import com.pseudocode.infovents.R;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    LocalStore mLocalstore;
    Firebase mFirebase;
    private AuthData mAuthData;
    private Firebase.AuthStateListener mAuthStateListener;
    private ProgressDialog mAuthProgressDialog;
    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginButton mFacebookLoginButton;
    private CallbackManager mFacebookCallbackManager;
    private AccessTokenTracker mFacebookAccessTokenTracker;

    public static final int RC_GOOGLE_LOGIN = 1;
    private GoogleApiClient mGoogleApiClient;
    private boolean mGoogleIntentInProgress;
    private boolean mGoogleLoginClicked;
    private ConnectionResult mGoogleConnectionResult;
    private SignInButton mGoogleLoginButton;

    private String gEMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        mFirebase = new Firebase(getResources().getString(R.string.firebaseRef));


        mLocalstore = new LocalStore(this);

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Singing in");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookLoginButton = (LoginButton) findViewById(R.id.btnFBLogin);
        mFacebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
                LoginActivity.this.onFacebookAccessTokenChange(currentAccessToken);
            }
        };

        /* Setup the Google API object to allow Google+ logins */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .build();

        mGoogleLoginButton = (SignInButton) findViewById(R.id.btnGoogleLogin);
        mGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleLoginClicked = true;
                if (!mGoogleApiClient.isConnecting()) {
                    if (mGoogleConnectionResult != null) {
                        resolveSignInError();
                    } else if (mGoogleApiClient.isConnected()) {
                        getGoogleOAuthTokenAndLogin();
                    } else {
                    /* connect API now */
                        Log.d(TAG, "Trying to connect to Google API");
                        mGoogleApiClient.connect();
                    }
                }
            }
        });



        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                mAuthProgressDialog.hide();
            }
        };
        mFirebase.addAuthStateListener(mAuthStateListener);



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLocalstore.getLoggedInUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> options = new HashMap<String, String>();
        if (requestCode == RC_GOOGLE_LOGIN) {
            /* This was a request by the Google API */
            if (resultCode != RESULT_OK) {
                mGoogleLoginClicked = false;
            }
            mGoogleIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }  else {
            /* Otherwise, it's probably the request by the Facebook login button, keep track of the session */
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void onFacebookAccessTokenChange(AccessToken token) {
        if (token != null) {
            mAuthProgressDialog.show();
            mFirebase.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
        } else {
            // Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout
            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
                mFirebase.unauth();
            }
        }
    }

    private void resolveSignInError() {
        if (mGoogleConnectionResult.hasResolution()) {
            try {
                mGoogleIntentInProgress = true;
                mGoogleConnectionResult.startResolutionForResult(this, RC_GOOGLE_LOGIN);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mGoogleIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void getGoogleOAuthTokenAndLogin() {
        /* Get OAuth token in Background */
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            String errorMessage = null;

            @Override
            protected String doInBackground(Void... params) {
                String token = null;

                try {
                    String scope = String.format("oauth2:%s", Scopes.PLUS_LOGIN);
                    token = GoogleAuthUtil.getToken(LoginActivity.this, Plus.AccountApi.getAccountName(mGoogleApiClient), scope);
                } catch (IOException transientEx) {
                    /* Network or server error */
                    Log.e(TAG, "Error authenticating with Google: " + transientEx);
                    errorMessage = "Network error: " + transientEx.getMessage();
                } catch (UserRecoverableAuthException e) {
                    Log.w(TAG, "Recoverable Google OAuth error: " + e.toString());
                    /* We probably need to ask for permissions, so start the intent if there is none pending */
                    if (!mGoogleIntentInProgress) {
                        mGoogleIntentInProgress = true;
                        Intent recover = e.getIntent();
                        startActivityForResult(recover, RC_GOOGLE_LOGIN);
                    }
                } catch (GoogleAuthException authEx) {
                    /* The call is not ever expected to succeed assuming you have already verified that
                     * Google Play services is installed. */
                    Log.e(TAG, "Error authenticating with Google: " + authEx.getMessage(), authEx);
                    errorMessage = "Error authenticating with Google: " + authEx.getMessage();
                }

                return token;
            }

            @Override
            protected void onPostExecute(String token) {
                mGoogleLoginClicked = false;
                if (token != null) {
                    /* Successfully got OAuth token, now login with Google */
                    mFirebase.authWithOAuthToken("google", token, new AuthResultHandler("google"));
                } else if (errorMessage != null) {
                    mAuthProgressDialog.hide();
                    showErrorDialog(errorMessage);
                }
            }
        };
        task.execute();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        float fbIconScale = 1.45f;
        Drawable drawable = this.getResources().getDrawable(
                com.facebook.R.drawable.com_facebook_button_icon);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*fbIconScale),
                (int)(drawable.getIntrinsicHeight()*fbIconScale));
        mFacebookLoginButton.setCompoundDrawables(drawable, null, null, null);
        mFacebookLoginButton.setCompoundDrawablePadding(this.getResources().
                getDimensionPixelSize(R.dimen.fb_margin_override_textpadding));
        mFacebookLoginButton.setPadding(
                this.getResources().getDimensionPixelSize(
                        R.dimen.fb_margin_override_lr),
                this.getResources().getDimensionPixelSize(
                        R.dimen.fb_margin_override_top),
                0,
                this.getResources().getDimensionPixelSize(
                        R.dimen.fb_margin_override_bottom));
    }

    @Override
    public void onConnected(final Bundle bundle) {
        /* Connected with Google API, use this to authenticate with Firebase */
        getGoogleOAuthTokenAndLogin();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mGoogleIntentInProgress) {
            /* Store the ConnectionResult so that we can use it later when the user clicks on the Google+ login button */
            mGoogleConnectionResult = result;

            if (mGoogleLoginClicked) {
                /* The user has already clicked login so we attempt to resolve all errors until the user is signed in,
                 * or they cancel. */
                resolveSignInError();
            } else {
                Log.e(TAG, result.toString());
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "huhu suspended");
    }


    private class AuthResultHandler implements Firebase.AuthResultHandler {

        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }


        @Override
        public void onAuthenticated(AuthData authData) {
            mAuthProgressDialog.hide();
            Map<String, String> map = new HashMap<String, String>();
            Map<String, String> map2 = (Map<String, String>) authData.getProviderData().get("cachedUserProfile");
            String email = "";


            map.put("provider", authData.getProvider());
            map.put("avatar", authData.getProviderData().get("profileImageURL").toString());

            if(authData.getProvider().equals("facebook")){
                map.put("first_name", map2.get("first_name"));
                map.put("last_name", map2.get("last_name"));
                map.put("gender", map2.get("gender"));
                map.put("name", map2.get("name"));
                map.put("link", map2.get("link"));
                email = (String) authData.getProviderData().get("email");
            }else {
                map.put("first_name", map2.get("given_name"));
                map.put("last_name", map2.get("family_name"));
                map.put("gender", map2.get("gender"));
                map.put("name", map2.get("name"));
                map.put("link", map2.get("link"));

                email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            }

            map.put("email", email);

            mFirebase.child("users").child(authData.getUid()).setValue(map);


            String id = authData.getUid();
            String fname = map.get("firstname");
            String lname = map.get("lastname");
            String name = map.get("name");
            String gender = map.get("gender");
            String link = map.get("link");
            String prov = map.get("provider");
            String avatar = map.get("avatar");


            Log.i(TAG, provider + " auth successful");
            User authUser = new User(id, fname, lname, name, gender, link, prov, avatar, email);
            LogUserIn(authUser);

        }


        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mAuthProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }


    private void LogUserIn(User returnedUser) {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        }
        mLocalstore.storeUserData(returnedUser);
        mLocalstore.setUserLoggedIn(true);
        startActivity(new Intent(this, MainActivity.class));
    }
}
