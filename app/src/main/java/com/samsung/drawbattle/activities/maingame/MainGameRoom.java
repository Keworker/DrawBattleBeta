package com.samsung.drawbattle.activities.maingame;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.app.FragmentTransaction;

import com.samsung.drawbattle.activities.Menu;
import com.samsung.drawbattle.activities.frommenu.Saved;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.LocalPersonalData;
import com.samsung.drawbattle.fragments.FriendFragment;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.RandFragment;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainGameRoom extends Activity implements View.OnClickListener {
    protected FrameLayout frameLayout;
    protected Button start;
    protected ImageButton player0, player1, player2, player3, player4, player5;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;
    protected FriendFragment friendFragment;
    protected RandFragment randFragment;
    protected final static String FRIEND_TAG = "Friend_Fragment";
    protected final static String RAND_TAG = "Rand_Fragment";
    protected ImageRes p0Res, p1Res, p2Res, p3Res, p4Res, p5Res;
    private float buttonSize;
    public float screenWidth, screenHeight;
    private static PrintWriter pW;
    private static Scanner in;
    private String users[] = new String[6];
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_room);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        buttonSize = size.y / 9.0f;
        frameLayout = findViewById(R.id.frameLayout);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);
        player0 = findViewById(R.id.player0);
        player0.setOnClickListener(this);
        p0Res = new ImageRes(R.drawable.add_player, player0, buttonSize, buttonSize);
        player1 = findViewById(R.id.player1);
        player1.setOnClickListener(this);
        p1Res = new ImageRes(R.drawable.add_player, player1, buttonSize, buttonSize);
        player2 = findViewById(R.id.player2);
        player2.setOnClickListener(this);
        p2Res = new ImageRes(R.drawable.add_player, player2, buttonSize, buttonSize);
        player3 = findViewById(R.id.player3);
        player3.setOnClickListener(this);
        p3Res = new ImageRes(R.drawable.add_player, player3, buttonSize, buttonSize);
        player4 = findViewById(R.id.player4);
        player4.setOnClickListener(this);
        p4Res = new ImageRes(R.drawable.add_player, player4, buttonSize, buttonSize);
        player5 = findViewById(R.id.player5);
        player5.setOnClickListener(this);
        p5Res = new ImageRes(R.drawable.add_player, player5, buttonSize, buttonSize);
        fragmentManager = getFragmentManager();
        if (MainGameSettings.getIfFriends()) {
            friendFragment = new FriendFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, friendFragment, FRIEND_TAG);
            fragmentTransaction.commit();

        }
        else {
            randFragment = new RandFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, randFragment, RAND_TAG);
            fragmentTransaction.commit();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("10.0.2.2", 5000);
                        pW = new PrintWriter(socket.getOutputStream());
                        pW.println("init/0/" + LocalPersonalData.getPersonName());
                        pW.flush();
                        in = new Scanner(socket.getInputStream());
                        new Thread() {
                            @Override
                            public void run() {
                                next:
                                while (true) {
                                    if (in.hasNext()) {
                                        String q = in.nextLine();
                                        String s[] = q.split("/");
                                        Log.d("Server", q);
                                        try {
                                            switch (s[0]) {
                                                case "init": {
                                                    String members[] = s[3].split(";");
                                                    for (int i = 0; i < members.length; i++) {
                                                        users[i] = members[i];
                                                    }
                                                    rebootAvas();
                                                    continue next;
                                                }
                                                case "request": {
                                                    switch(s[1]) {
                                                        ////nen ghj,ktvs rfrbt nj ,t z ecnfk
                                                        case "run": {
                                                            Log.d("Server", "123");
                                                            String teams[] = s[2].split(",");
                                                            for (int i = 0; i < 2; i++) {
                                                                users[i] = teams[i];
                                                            }
                                                            Intent intent = new Intent(context, MainGameActivity.class);
                                                            startActivity(intent);
                                                            break;
                                                        }
                                                        default: {
                                                            Log.d("Server", "134");
                                                        }
                                                    }
                                                    break;
                                                }
                                                case "exit": {
                                                    if (Integer.parseInt(s[1]) == 200) {
                                                        Intent intent = new Intent(context, Menu.class);
                                                        startActivity(intent);
                                                        return;
                                                    }
                                                    break;
                                                }
                                                case "new": {
                                                    int i = 0;
                                                    while (users[i] != null) {
                                                        i++;
                                                    }
                                                    users[i] = s[1];
                                                    rebootAvas();
                                                    break;
                                                }
                                            }
                                        }
                                        catch (ArrayIndexOutOfBoundsException exception) {
                                            exception.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }.start();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Server", e.toString());
                        Log.d("Server", "###Fail###");
                    }
                }
            }.start();
        }
    }

    private void rebootAvas() {
        p0Res = new ImageRes(
                (users[0] == null ? R.drawable.add_player :
                        R.drawable.avatar1), player0,
                buttonSize, buttonSize);
        p1Res = new ImageRes(
                (users[1] == null ? R.drawable.add_player :
                        R.drawable.avatar2), player1,
                buttonSize, buttonSize);
        p2Res = new ImageRes(
                (users[2] == null ? R.drawable.add_player :
                        R.drawable.avatar3), player2,
                buttonSize, buttonSize);
        p3Res = new ImageRes(
                (users[3] == null ? R.drawable.add_player :
                        R.drawable.avatar4), player3,
                buttonSize, buttonSize);
        p4Res = new ImageRes(
                (users[4] == null ? R.drawable.add_player :
                        R.drawable.avatar5), player4,
                buttonSize, buttonSize);
        p5Res = new ImageRes(
                (users[5] == null ? R.drawable.add_player :
                        R.drawable.avatar6), player5,
                buttonSize, buttonSize);
    }

    @Override
    public void onBackPressed() {
        new Thread() {
            @Override
            public void run() {
                pW.println("exit/");
                pW.flush();
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player0:{

                break;
            }
            case R.id.player1:{

                break;
            }
            case R.id.player2:{

                break;
            }
            case R.id.player3:{

                break;
            }
            case R.id.player4:{

                break;
            }
            case R.id.player5:{

                break;
            }
            case R.id.start:{
                new Thread() {
                    @Override
                    public void run() {
                        pW.println("run/try");
                        pW.flush();
                    }
                }.start();
                break;
            }
        }
    }

    public static PrintWriter getpW() {
        return pW;
    }

    public static Scanner getIn() {
        return in;
    }
}