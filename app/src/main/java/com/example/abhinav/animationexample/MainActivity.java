package com.example.abhinav.animationexample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button animateButton;
    Button button;
    AnimatorSet animatorSet=new AnimatorSet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateButton=(Button)findViewById(R.id.button);
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button2
                && animatorSet!=null && !animatorSet.isStarted())
        {
            final ValueAnimator valueAnimatorY2=ValueAnimator.ofFloat(100f,50f,0f);
            final ValueAnimator valueAnimatorY=ValueAnimator.ofFloat(0f,50f,100f);
            valueAnimatorY2.setDuration(1500);
            valueAnimatorY.setDuration(1500);

            valueAnimatorY2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float)animation.getAnimatedValue();
                    animateButton.setTranslationY(animatedValue);
                    float x= (float) Math.sqrt((2500-Math.pow(animatedValue-50,2)));
                    animateButton.setTranslationX(-x);
                }
            });
            valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float)animation.getAnimatedValue();
                    animateButton.setTranslationY(animatedValue);
                    float x= (float) Math.sqrt((2500-Math.pow(animatedValue-50,2)));
                    animateButton.setTranslationX(x);
                }

            });
            valueAnimatorY2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animatorSet.start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            animatorSet.play(valueAnimatorY).before(valueAnimatorY2);
            animatorSet.start();
        }
    }
}
