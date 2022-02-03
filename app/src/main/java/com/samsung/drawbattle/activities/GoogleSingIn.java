package com.samsung.drawbattle.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.LocalPersonalData;
import com.samsung.drawbattle.R;

public class GoogleSingIn extends Activity implements View.OnClickListener {
    protected static SignInButton signInButton;
    protected static GoogleSignInClient googleSignInClient;
    protected static int RC_SIGN_IN = 0;
    protected static SharedPreferences sPref;
    protected ImageView googleImage;
    private ImageRes googleImageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        googleImage = findViewById(R.id.googleImage);
        googleImageRes = new ImageRes(R.drawable.google, googleImage,
                (((size.y / 3.0f) * 196) / 334.0f), size.y / 3.0f);
        sPref = getSharedPreferences("AndroidManifest.xml", 0);
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        if (sPref.getBoolean("alreadySignIn", true)) {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sing_in);
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInButton: {
                signIn();
                break;
            }
        }
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.
                    getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            setCashTrue();
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            LocalPersonalData.setAccountData(account.getDisplayName(),
                    account.getGivenName(),
                    account.getFamilyName(),
                    account.getEmail(),
                    account.getId());
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        }
        catch (ApiException e) {
            Log.w("Exceptions", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public static GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    public static void setCashFalse() {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean("alreadySignIn", false);
        ed.apply();
    }

    public static void setCashTrue() {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean("alreadySignIn", true);
        ed.apply();
    }
}