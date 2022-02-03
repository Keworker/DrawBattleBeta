package com.samsung.drawbattle.activities.maingame;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.app.FragmentTransaction;

import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.fragments.FriendFragment;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.RandFragment;

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
        p0Res = new ImageRes(R.drawable.avatar1, player0,
                buttonSize, buttonSize);
        player1 = findViewById(R.id.player1);
        player1.setOnClickListener(this);
        p1Res = new ImageRes(R.drawable.add_player, player1,
                buttonSize, buttonSize);
        player2 = findViewById(R.id.player2);
        player2.setOnClickListener(this);
        p2Res = new ImageRes(R.drawable.add_player, player2,
                buttonSize, buttonSize);
        player3 = findViewById(R.id.player3);
        player3.setOnClickListener(this);
        p3Res = new ImageRes(R.drawable.add_player, player3,
                buttonSize, buttonSize);
        player4 = findViewById(R.id.player4);
        player4.setOnClickListener(this);
        p4Res = new ImageRes(R.drawable.add_player, player4,
                buttonSize, buttonSize);
        player5 = findViewById(R.id.player5);
        player5.setOnClickListener(this);
        p5Res = new ImageRes(R.drawable.add_player, player5,
                buttonSize, buttonSize);
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
        }
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
                //Условие, что размер массива игроков в комнате равен 6
                Intent intent = new Intent(this, MainGameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }
}