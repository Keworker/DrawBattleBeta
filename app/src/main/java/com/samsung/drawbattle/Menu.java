package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Menu extends Activity implements View.OnClickListener {
    protected Button settings, start, saved;
    public static SharedPreferences readRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);
        saved = findViewById(R.id.saved);
        saved.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            LocalPersonalData.setAccountData(acct.getDisplayName(),
                    acct.getGivenName(),
                    acct.getFamilyName(),
                    acct.getEmail(),
                    acct.getId());
        }

        readRules = getPreferences(MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings:{
                Intent intent = new Intent(this, Settings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.start:{
                Intent intent = new Intent(this, ChooseGame.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.saved:{
                Intent intent = new Intent(this, Saved.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }
}