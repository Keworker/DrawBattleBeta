package com.samsung.drawbattle;

import android.app.Activity;
import android.os.Bundle;

public class ChooseGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        /*Now this Activity is empty, but if we have a lot of game modes,
        in this Activity it will be possible to choose*/
    }
}