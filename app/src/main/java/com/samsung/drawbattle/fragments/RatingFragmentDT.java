package com.samsung.drawbattle.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.drawtournament.DrawTournamentActivity;
import com.samsung.drawbattle.classes.ImageRes;

public class RatingFragmentDT extends Fragment implements View.OnClickListener {
    protected ImageButton star1, star2, star3, star4, star5, star6, star7, star8, star9, star10;
    protected ImageRes starRes1, starRes2, starRes3, starRes4, starRes5,
            starRes6, starRes7, starRes8, starRes9, starRes10;
    private byte rate = 5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating_dtf, container, false);
        star1 = view.findViewById(R.id.star1DTF);
        star2 = view.findViewById(R.id.star2DTF);
        star3 = view.findViewById(R.id.star3DTF);
        star4 = view.findViewById(R.id.star4DTF);
        star5 = view.findViewById(R.id.star5DTF);
        star6 = view.findViewById(R.id.star6DTF);
        star7 = view.findViewById(R.id.star7DTF);
        star8 = view.findViewById(R.id.star8DTF);
        star9 = view.findViewById(R.id.star9DTF);
        star10 = view.findViewById(R.id.star10DTF);
        setLayoutValues();
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);
        star6.setOnClickListener(this);
        star7.setOnClickListener(this);
        star8.setOnClickListener(this);
        star9.setOnClickListener(this);
        star10.setOnClickListener(this);
        return view;
    }

    public void setLayoutValues() {
        int starHW = Math.min((DrawTournamentActivity.toolbarHeight - 10) / 10,
                DrawTournamentActivity.toolbarWidth - 10);
        starRes1 = new ImageRes(R.drawable.star_full, star1, starHW, starHW);
        starRes2 = new ImageRes(R.drawable.star_full, star2, starHW, starHW);
        starRes3 = new ImageRes(R.drawable.star_full, star3, starHW, starHW);
        starRes4 = new ImageRes(R.drawable.star_full, star4, starHW, starHW);
        starRes5 = new ImageRes(R.drawable.star_full, star5, starHW, starHW);
        starRes6 = new ImageRes(R.drawable.star_void, star6, starHW, starHW);
        starRes7 = new ImageRes(R.drawable.star_void, star7, starHW, starHW);
        starRes8 = new ImageRes(R.drawable.star_void, star8, starHW, starHW);
        starRes9 = new ImageRes(R.drawable.star_void, star9, starHW, starHW);
        starRes10 = new ImageRes(R.drawable.star_void, star10, starHW, starHW);
    }

    @Override
    public void onClick(View view) {
        rate = 0;
        star10.setBackgroundResource(R.drawable.star_void);
        star9.setBackgroundResource(R.drawable.star_void);
        star8.setBackgroundResource(R.drawable.star_void);
        star7.setBackgroundResource(R.drawable.star_void);
        star6.setBackgroundResource(R.drawable.star_void);
        star5.setBackgroundResource(R.drawable.star_void);
        star4.setBackgroundResource(R.drawable.star_void);
        star3.setBackgroundResource(R.drawable.star_void);
        star2.setBackgroundResource(R.drawable.star_void);
        star1.setBackgroundResource(R.drawable.star_void);
        switch (view.getId()) {
            case R.id.star10DTF: {
                rate++;
                star10.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star9DTF: {
                rate++;
                star9.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star8DTF: {
                rate++;
                star8.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star7DTF: {
                rate++;
                star7.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star6DTF: {
                rate++;
                star6.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star5DTF: {
                rate++;
                star5.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star4DTF: {
                rate++;
                star4.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star3DTF: {
                rate++;
                star3.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star2DTF: {
                rate++;
                star2.setBackgroundResource(R.drawable.star_full);
            }
            case R.id.star1DTF: {
                rate++;
                star1.setBackgroundResource(R.drawable.star_full);
            }
        }
    }

    public byte getRate() {
        return rate;
    }
}
