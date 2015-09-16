package com.smartman.game3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotManager;

public class MainActivity extends Activity {

    private static MainActivity mainActivity = null;
    private TextView scoreView;
    private GameView gameView;
    private LinearLayout pre;
    private ImageView imageView;
    private Button restartBtn;
    private Button exitBtn;
    private AnimLayer animLayer = null;
    private static Boolean isExit = false;
    private TextView best2;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        scoreView = (TextView) findViewById(R.id.tvScore);
        scoreView.setText(score + "");

        //获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        int mScreenHeight = dm.heightPixels;

        int width = Math.min(mScreenWidth, mScreenHeight) - 50;
        Config.ANIMATION_LEFT = (Math.min(mScreenWidth, mScreenHeight) - width) / 2;

        //设置pre的Linearlayout
        pre = (LinearLayout) findViewById(R.id.pre);
        ((LinearLayout.LayoutParams) pre.getLayoutParams()).setMargins(Config.ANIMATION_LEFT, Config.ANIMATION_TOP, Config.ANIMATION_LEFT, 0);

        //设置GameView
        gameView = (GameView) findViewById(R.id.gameView);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) gameView.getLayoutParams();
        params.width = width;
        params.height = width;
        params.setMargins(Config.ANIMATION_LEFT, Config.ANIMATION_TOP, Config.ANIMATION_LEFT, 5);


        //设置big num
        imageView = (ImageView) findViewById(R.id.big);
        imageView.getLayoutParams().width = (int) (width / 2.3);
        imageView.getLayoutParams().height = (int) (width / 2.3);

        //有米广告
        //实例化广告条
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        //获取要嵌入广告条的布局
        LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
        //将广告条加入到布局中
         adLayout.addView(adView);


        animLayer = (AnimLayer) findViewById(R.id.animLayer);

        best2 = (TextView) findViewById(R.id.best2);

        restartBtn = (Button) findViewById(R.id.restart);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.startGame();
            }
        });

        exitBtn = (Button) findViewById(R.id.exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExit == false) {
                    isExit = true;
                    Toast.makeText(getMainActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    return;
                }
                getMainActivity().finish();
            }
        });

    }

    private int score = 0;

    public void addScore(int num) {
        score += num;
        scoreView.setText(score + "");
    }


    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public AnimLayer getAnimLayer() {
        return animLayer;
    }


    public void saveBestScore() {
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int num = pref.getInt("score", 0);
        if (score > num) {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putInt("score", score);
            editor.commit();
        }
    }

    public void getBestScore() {
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int num = pref.getInt("score", 0);
        best2.setText(num + "");
    }


    @Override
    protected void onStop() {
        // 如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
        SpotManager.getInstance(this).onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SpotManager.getInstance(this).onDestroy();
        super.onDestroy();
    }

}
