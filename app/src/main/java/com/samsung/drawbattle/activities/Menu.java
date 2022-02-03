package com.samsung.drawbattle.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.samsung.drawbattle.activities.frommenu.ChooseGame;
import com.samsung.drawbattle.activities.frommenu.Saved;
import com.samsung.drawbattle.activities.frommenu.Settings;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.LocalPersonalData;
import com.samsung.drawbattle.R;

public class Menu extends Activity implements View.OnClickListener {
    protected Button settings, start, saved;
    public static SharedPreferences readRules;
    public float screenWidth, screenHeight;
    protected ImageRes preview;
    protected ImageView imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        imagePreview = findViewById(R.id.preview);
        preview = new ImageRes(R.drawable.preview, imagePreview,
                screenWidth / 3 * 2, screenWidth / 3 * 2 / 16 * 9);
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