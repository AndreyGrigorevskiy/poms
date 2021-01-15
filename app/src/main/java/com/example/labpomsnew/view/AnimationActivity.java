package com.example.labpomsnew.view;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.labpomsnew.R;

public class AnimationActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        image = findViewById(R.id.imageViewHeal);

        final Animation animationRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        final Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animationMove = AnimationUtils.loadAnimation(this, R.anim.anim_move);
        final Animation animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        Button buttonRotate = findViewById(R.id.buttonRotate);
        buttonRotate.setOnClickListener(v -> animate(animationRotate));
        Button buttonScale = findViewById(R.id.buttonScale);
        buttonScale.setOnClickListener(v -> animate(animationScale));
        Button buttonMove = findViewById(R.id.buttonMove);
        buttonMove.setOnClickListener(v -> animate(animationMove));
        Button buttonAlpha = findViewById(R.id.buttonAlpha);
        buttonAlpha.setOnClickListener(v -> animate(animationAlpha));

    }

    private void animate(Animation animation) {image.startAnimation(animation);}
}