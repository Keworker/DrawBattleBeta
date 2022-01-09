package com.samsung.drawbattle;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.app.FragmentTransaction;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_room);
        frameLayout = findViewById(R.id.frameLayout);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);
        player0 = findViewById(R.id.player0);
        player0.setOnClickListener(this);
        player1 = findViewById(R.id.player1);
        player1.setOnClickListener(this);
        player2 = findViewById(R.id.player2);
        player2.setOnClickListener(this);
        player3 = findViewById(R.id.player3);
        player3.setOnClickListener(this);
        player4 = findViewById(R.id.player4);
        player4.setOnClickListener(this);
        player5 = findViewById(R.id.player5);
        player5.setOnClickListener(this);
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