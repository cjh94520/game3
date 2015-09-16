package com.smartman.game3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by jiahui.chen on 2015/9/8.
 */
public class Card extends FrameLayout {
    public TextView label;
    private View background;
    public Card(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        LayoutParams lp = null;

        background = new View(getContext());
        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        background.setBackgroundColor(getResources().getColor(R.color.num0));
        addView(background, lp);

        label = new TextView(getContext());
        label.setTextSize(36);
        label.setGravity(Gravity.CENTER);

        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    public Card(Context context) {
        this(context,null);
    }

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if(num <= 0 )
        {
            label.setText("");
        }
        else {
            label.setText(num + "");
        }
        if(num == 0)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num0));
        }
        else if(num == 2)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num2));
        }
        else if(num == 4)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num4));
        }
        else if(num == 8)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num8));
        }
        else if(num == 16)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num16));
        }
        else if(num == 32)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num32));
        }
        else if(num == 64)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num64));
        }
        else if(num == 128)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num128));
        }
        else if(num == 256)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num256));
        }
        else if(num == 512)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num512));
        }
        else if(num == 1024)
        {
            label.setBackgroundColor(getResources().getColor(R.color.num1024));
        }
        else
        {
            label.setBackgroundColor(getResources().getColor(R.color.num2048));
        }
    }

    public TextView getLabel()
    {
        return label;
    }
    public boolean equal(Card c) {
        return this.getNum() == c.getNum();
    }
}
