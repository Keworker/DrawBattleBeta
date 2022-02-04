package com.samsung.drawbattle.activities.frommenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.GoogleSingIn;
import com.samsung.drawbattle.activities.Menu;
import com.samsung.drawbattle.classes.LocalPersonalData;

public class Settings extends Activity implements View.OnClickListener {
    protected Button back, singOut;
    protected TextView aboutDev, accountName;
    protected boolean sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        singOut = findViewById(R.id.signOut);
        singOut.setOnClickListener(this);
        accountName = findViewById(R.id.accountName);
        try {
            if (LocalPersonalData.getPersonName().equals("")) {
                throw new NullPointerException();
            }
            accountName.setText(LocalPersonalData.getPersonName());
            singOut.setText(R.string.singOut);
        }
        catch (NullPointerException npe) {
            accountName.setText(R.string.googleTextError);
            singOut.setText(R.string.googleButtonError);
        }
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        aboutDev = findViewById(R.id.aboutDev);
        if (aboutDev != null) {
            aboutDev.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                Intent intent = new Intent(this, Menu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.signOut: {
                createDialog(getString(R.string.app_name), getString(R.string.question));
                if (sure) {
                    singOut();
                    GoogleSingIn.setCashFalse();
                    Intent intent = new Intent(this, GoogleSingIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                break;
            }
        }
    }

    private void createDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton(getText(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sure = false;
                    }
                });
        builder.setPositiveButton(getText(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sure = true;
                    }
                });
        builder.show();
    }

    private void singOut() {
        GoogleSingIn.getGoogleSignInClient().signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}