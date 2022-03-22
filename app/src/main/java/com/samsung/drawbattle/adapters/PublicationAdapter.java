package com.samsung.drawbattle.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    ImageButton collection1;
    ImageButton collection2;
    ImageButton collection3;
    ImageButton collection4;
    ImageButton collection5;
    ImageButton collection6;
    boolean inCol1, inCol2, inCol3, inCol4, inCol5, inCol6;

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
                    .inflate(R.layout.publication_item, null);
        }
        init(convertView, list.get(position));
        return convertView;
    }

    private void init(View view, PublicationMG p) {
        TextView name1 = view.findViewById(R.id.name1P);
        name1.setText(p.getName1());
        TextView name2 = view.findViewById(R.id.name2P);
        name2.setText(p.getName2());
        TextView name3 = view.findViewById(R.id.name3P);
        name3.setText(p.getName3());
        TextView name4 = view.findViewById(R.id.name4P);
        name4.setText(p.getName4());
        TextView name5 = view.findViewById(R.id.name5P);
        name5.setText(p.getName5());
        TextView name6 = view.findViewById(R.id.name6P);
        name6.setText(p.getName6());
        ImageView av1 = view.findViewById(R.id.av1P);
        ImageView av2 = view.findViewById(R.id.av2P);
        ImageView av3 = view.findViewById(R.id.av3P);
        ImageView av4 = view.findViewById(R.id.av4P);
        ImageView av5 = view.findViewById(R.id.av5P);
        ImageView av6 = view.findViewById(R.id.av6P);
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
        switch (p.getAv3()) {
            case 1: {
                av3.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av3.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av3.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av3.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av3.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av3.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        switch (p.getAv4()) {
            case 1: {
                av4.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av4.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av4.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av4.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av4.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av4.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        switch (p.getAv5()) {
            case 1: {
                av5.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av5.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av5.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av5.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av5.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av5.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        switch (p.getAv6()) {
            case 1: {
                av6.setBackgroundResource(R.drawable.avatar1);
                break;
            }
            case 2: {
                av6.setBackgroundResource(R.drawable.avatar2);
                break;
            }
            case 3: {
                av6.setBackgroundResource(R.drawable.avatar3);
                break;
            }
            case 4: {
                av6.setBackgroundResource(R.drawable.avatar4);
                break;
            }
            case 5: {
                av6.setBackgroundResource(R.drawable.avatar5);
                break;
            }
            case 6: {
                av6.setBackgroundResource(R.drawable.avatar6);
                break;
            }
        }
        TextView text1 = view.findViewById(R.id.text1P);
        text1.setText(p.getText1());
//        ImageView imageView2 = view.findViewById(R.id.image2P);
        TextView text3 = view.findViewById(R.id.text3P);
        text3.setText(p.getText3());
//        ImageView imageView4 = view.findViewById(R.id.image4P);
        TextView text5 = view.findViewById(R.id.text5P);
        text5.setText(p.getText5());
//        ImageView imageView6 = view.findViewById(R.id.image6P);
        like = view.findViewById(R.id.likeP);
        likeC = view.findViewById(R.id.likeCountP);
        like.setOnClickListener(this);
        collection1 = view.findViewById(R.id.collection1P);
        collection2 = view.findViewById(R.id.collection2P);
        collection3 = view.findViewById(R.id.collection3P);
        collection4 = view.findViewById(R.id.collection4P);
        collection5 = view.findViewById(R.id.collection5P);
        collection6 = view.findViewById(R.id.collection6P);
        View.OnClickListener collectionClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.collection1P: {
                        if (inCol1) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol1 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol1 = true;
                        }
                        break;
                    }
                    case R.id.collection2P: {
                        if (inCol2) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol2 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol2 = true;
                        }
                        break;
                    }
                    case R.id.collection3P: {
                        if (inCol3) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol3 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol3 = true;
                        }
                        break;
                    }
                    case R.id.collection4P: {
                        if (inCol4) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol4 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol4 = true;
                        }
                        break;
                    }
                    case R.id.collection5P: {
                        if (inCol5) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol5 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol5 = true;
                        }
                        break;
                    }
                    case R.id.collection6P: {
                        if (inCol6) {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.to_collections);
                            //Убираем его из коллекции
                            inCol6 = false;
                        }
                        else {
                            (view.findViewById(id)).setBackgroundResource(R.drawable.from_collections);
                            //Добавляем его в коллекцию
                            inCol6 = true;
                        }
                        break;
                    }
                }
            }
        };
        collection1.setOnClickListener(collectionClick);
        collection2.setOnClickListener(collectionClick);
        collection3.setOnClickListener(collectionClick);
        collection4.setOnClickListener(collectionClick);
        collection5.setOnClickListener(collectionClick);
        collection6.setOnClickListener(collectionClick);
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
