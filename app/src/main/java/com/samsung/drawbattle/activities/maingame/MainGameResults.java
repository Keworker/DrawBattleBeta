package com.samsung.drawbattle.activities.maingame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.Menu;
import com.samsung.drawbattle.adapters.PublicationAdapter;
import com.samsung.drawbattle.classes.PublicationMG;

import java.util.ArrayList;
import java.util.List;

public class MainGameResults extends Activity implements View.OnClickListener {
    Button exit;
    ListView listMGRes;
    ArrayList<PublicationMG> list = new ArrayList<PublicationMG>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_results);
        exit = findViewById(R.id.exitMG);
        exit.setOnClickListener(this);
        listMGRes = findViewById(R.id.listMGRes);

        list.add(new PublicationMG(3, 1, 2, 4, 5, 6,
                "Keworker", "Sky", "Is",
                "Java", "Developer", "Yes",
                "Hello World", R.id.preview, "Hello Java",
                R.id.preview, "Haloman", R.id.preview));
        list.add(new PublicationMG(3, 1, 2, 4, 5, 6,
                "Keworker", "Sky", "Is",
                "Java", "Developer", "Yes",
                "Hello World", R.id.preview, "Hello Java",
                R.id.preview, "Haloman", R.id.preview));
        listMGRes.setAdapter(new PublicationAdapter(this, list));
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