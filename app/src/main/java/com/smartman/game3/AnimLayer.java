package com.smartman.game3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahui.chen on 2015/9/15.
 */
public class AnimLayer extends FrameLayout {

    public AnimLayer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayer();
    }

    public AnimLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayer();
    }

    public AnimLayer(Context context) {
        super(context);
        initLayer();
    }

    private void initLayer() {
    }

    public void createMoveAnim(final Card from, final Card to, int fromX, int toX, int fromY, int toY) {

        final Card c = getCard(from.getNum());

        LayoutParams lp = new LayoutParams(Config.CARD_WIDTH, Config.CARD_WIDTH);
        lp.leftMargin = fromY * Config.CARD_WIDTH + Config.ANIMATION_LEFT;
        lp.topMargin = fromX * Config.CARD_WIDTH + Config.ANIMATION_TOP;
        c.setLayoutParams(lp);


        if (to.getNum() <= 0) {
            to.getLabel().setVisibility(View.INVISIBLE);
        }
        TranslateAnimation ta = new TranslateAnimation(0,Config.CARD_WIDTH * (toY - fromY), 0, Config.CARD_WIDTH * (toX - fromX));
        ta.setDuration(80);
        ta.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                to.getLabel().setVisibility(View.VISIBLE);
                recycleCard(c);
            }
        });
        c.startAnimation(ta);
    }

    private Card getCard(int num) {
        Card c;
        if (cards.size() > 0) {
            c = cards.remove(0);
        } else {
            c = new Card(getContext());
            addView(c);
        }
        c.setVisibility(View.VISIBLE);
        c.setNum(num);
        return c;
    }

    private void recycleCard(Card c) {
        c.setVisibility(View.INVISIBLE);
        c.setAnimation(null);
        cards.add(c);
    }

    private List<Card> cards = new ArrayList<Card>();

    public void createScaleTo1(Card target) {
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(160);
        target.setAnimation(null);
        target.getLabel().startAnimation(sa);
    }

    public void updateScore(final TextView textView)
    {
        TranslateAnimation ta = new TranslateAnimation(-10, 0, -10, 10);
        ta.setDuration(80);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               // textView.gett
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(ta);
    }

}

