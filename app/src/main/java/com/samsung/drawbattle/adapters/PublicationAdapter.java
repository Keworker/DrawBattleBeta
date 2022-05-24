package com.samsung.drawbattle.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.maingame.MainGameResults;
import com.samsung.drawbattle.classes.PublicationMG;

import java.util.List;

public class PublicationAdapter extends ArrayAdapter<PublicationMG> implements View.OnClickListener {
    List <PublicationMG> list;
    MainGameResults activity;
    ImageButton like;
    TextView likeC;
    Boolean isLike = false;

    public PublicationAdapter(@NonNull Context context, @NonNull List<PublicationMG> objects) {
        super(context, R.layout.publication_item, objects);
        list = objects;
        activity = (MainGameResults) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.publication_item,  parent, false);
        }
        init(convertView, list.get(position));
        return convertView;
    }

    private void init(View view, PublicationMG p) {
        TextView name1 = view.findViewById(R.id.name1P);
        name1.setText(p.getName1());
        TextView name2 = view.findViewById(R.id.name2P);
        name2.setText(p.getName2());
        ImageView av1 = view.findViewById(R.id.av1P);
        ImageView av2 = view.findViewById(R.id.av2P);
        switch (p.getAv1()) {
            case 1: {
                av1.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av1.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av1.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av1.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av1.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av1.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        switch (p.getAv2()) {
            case 1: {
                av2.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av2.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av2.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av2.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av2.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av2.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        TextView text1 = view.findViewById(R.id.text1P);
        text1.setText(p.getText1());
        ImageView imageView2 = view.findViewById(R.id.image2P);
        p.setI(imageView2);
        p.setT(text1);
        like = view.findViewById(R.id.likeP);
        likeC = view.findViewById(R.id.likeCountP);
        like.setOnClickListener(this);
    }

    public void init(int i, String x) {
        PublicationMG p = list.get(i);
        p.setImage2(x);
        ImageView imageView2 = p.getI();
        Bitmap bitmap = Bitmap.createBitmap(imageView2.getWidth(),
                imageView2.getWidth() / 16 * 9, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(new Gson().fromJson(p.getImage2(), int[].class),
                0, imageView2.getWidth(), 0, 0,
                imageView2.getWidth(), imageView2.getWidth() / 16 * 9);
        imageView2.setImageBitmap(bitmap);
    }

    public TextView init(int i) {
        return list.get(i).getT();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.likeP: {
                if (isLike) {
                    like.setBackgroundResource(R.drawable.ic_baseline_like_void);
                    likeC.setText(Integer
                            .toString(Integer.parseInt(likeC.getText().toString()) - 1));
                    isLike = false;
                }
                else {
                    like.setBackgroundResource(R.drawable.ic_baseline_like_full);
                    likeC.setText(Integer
                            .toString(Integer.parseInt(likeC.getText().toString()) + 1));
                    isLike = true;
                }
                break;
            }

        }
    }
}
