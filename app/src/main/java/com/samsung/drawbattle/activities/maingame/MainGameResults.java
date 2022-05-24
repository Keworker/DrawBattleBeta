package com.samsung.drawbattle.activities.maingame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.Menu;
import com.samsung.drawbattle.adapters.PublicationAdapter;
import com.samsung.drawbattle.classes.PublicationMG;

import java.util.ArrayList;
import java.util.List;

public class MainGameResults extends Activity implements View.OnClickListener {
    public static MainGameResults res;
    Handler handler;
    Button exit;
    ListView listMGRes;
    ArrayList<PublicationMG> list = new ArrayList<PublicationMG>();
    String t1 = "Some text", t2 = "SomeText", i1 = "[1]", i2 = "[1]";
    PublicationAdapter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_results);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                String s = (String) msg.obj;
                switch (msg.arg1) {
                    case 0: {
                        MainGameResults.res.p.init(0).setText(s);
                        break;
                    }
                    case 1: {
                        MainGameResults.res.p.init(1).setText(s);
                        break;
                    }
                    case 2: {
                        MainGameResults.res.p.init(0, s);
                        break;
                    }
                    case 3: {
                        MainGameResults.res.p.init(1, s);
                        break;
                    }
                }
            }
        };
        res = this;
        exit = findViewById(R.id.exitMG);
        exit.setOnClickListener(this);
        listMGRes = findViewById(R.id.listMGRes);
        list.add(new PublicationMG(1, 2, "First player", "Second player", t1, i2));
        list.add(new PublicationMG(2, 1, "Second player", "First player", t2, i1));
        p = new PublicationAdapter(this, list);
        listMGRes.setAdapter(p);
    }

    public void set(int i, String s) {
        p.init(i, s);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exitMG: {
                Intent intent = new Intent(this, Menu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }
}