package com.smartman.game3;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahui.chen on 2015/9/8.
 */
public class GameView extends LinearLayout {

    private Card[][] cards = new Card[4][4];
    private List<Point> points = new ArrayList<>();

    private String TAG = "GameView";

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }


    private void initGameView() {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundResource(R.drawable.gameview);

        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetY) > Math.abs(offsetX)) {
                            if (offsetY > 5) {
                                swipeDown();
                            } else if (offsetY < -5) {
                                swipeUp();
                            }
                        } else {
                            if (offsetX > 5) {
                                swipeRight();
                            } else if (offsetX < -5) {
                                swipeLeft();
                            }
                        }
                        break;
                }


                return true;
            }
        });
    }

    private void swipeLeft() {
        boolean isMerge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int z = y + 1; z < 4; z++) {
                    if (cards[x][z].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][z], cards[x][y], x, x, z, y);
                            cards[x][y].setNum(cards[x][z].getNum());
                            cards[x][z].setNum(0);
                            y--;
                            isMerge = true;
                        } else if (cards[x][z].equal(cards[x][y])) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][z], cards[x][y], x, x, z, y);
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            cards[x][z].setNum(0);
                            isMerge = true;
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum() * 2);
                        }
                        break;
                    }

                }
            }
        }
        if (isMerge) {
            addRandomNum();
            checkGameOver();
        }
    }

    private void swipeRight() {
        boolean isMerge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y > 0; y--) {
                for (int z = y - 1; z >= 0; z--) {
                    if (cards[x][z].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][z], cards[x][y], x, x, z, y);
                            cards[x][y].setNum(cards[x][z].getNum());
                            cards[x][z].setNum(0);
                            y++;
                            isMerge = true;
                        } else if (cards[x][z].equal(cards[x][y])) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][z], cards[x][y], x, x, z, y);
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            cards[x][z].setNum(0);
                            isMerge = true;
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum() * 2);
                        }
                        break;
                    }

                }
            }
        }
        if (isMerge) {
            addRandomNum();
            checkGameOver();
        }
    }

    private void swipeUp() {
        boolean isMerge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int z = x + 1; z < 4; z++) {
                    if (cards[z][y].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[z][y], cards[x][y], z, x, y, y);
                            cards[x][y].setNum(cards[z][y].getNum());
                            cards[z][y].setNum(0);
                            x--;
                            isMerge = true;
                        } else if (cards[z][y].equal(cards[x][y])) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[z][y], cards[x][y], z, x, y, y);
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            cards[z][y].setNum(0);
                            isMerge = true;
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum() * 2);
                        }
                        break;
                    }

                }
            }
        }
        if (isMerge) {
            addRandomNum();
            checkGameOver();
        }
    }

    private void swipeDown() {
        boolean isMerge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x > 0; x--) {
                for (int z = x - 1; z >= 0; z--) {
                    if (cards[z][y].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[z][y], cards[x][y], z, x, y, y);
                            cards[x][y].setNum(cards[z][y].getNum());
                            cards[z][y].setNum(0);
                            x++;
                            isMerge = true;
                        } else if (cards[z][y].equal(cards[x][y])) {
                            MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[z][y], cards[x][y], z, x, y, y);
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            cards[z][y].setNum(0);
                            isMerge = true;
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum() * 2);
                        }
                        break;
                    }

                }
            }
        }
        if (isMerge) {
            addRandomNum();
            checkGameOver();
        }
    }

    private void checkGameOver() {
        boolean isCompleted = true;

        ALL:

        for (int x = 0; x < 4 ; x++) {
            for (int y = 0; y < 4 ; y++) {
                if ((cards[x][y].getNum() == 0) ||
                        (x > 1 && cards[x][y].equal(cards[x - 1][y])) ||
                        (x < 3 && cards[x][y].equal(cards[x + 1][y])) ||
                        (y > 1 && cards[x][y].equal(cards[x][y - 1])) ||
                        (y < 3 && cards[x][y].equal(cards[x][y + 1]))) {
                    isCompleted = false;
                    break  ALL;
                }
            }
        }

        if (isCompleted) {
            MainActivity.getMainActivity().saveBestScore();
            new AlertDialog.Builder(getContext())
                    .setTitle("你好")
                    .setMessage("游戏结束")
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startGame();
                        }
                    }).show();
        }
    }


    private void addCard(int width, int height) {
        Card c;
        LinearLayout line;
        LinearLayout.LayoutParams lineLp;
        for (int x = 0; x < 4; x++) {
            line = new LinearLayout(getContext());
            lineLp = new LinearLayout.LayoutParams(-1, height);
            addView(line, lineLp);
            for (int y = 0; y < 4; y++) {
                c = new Card(getContext());
                line.addView(c, width, height);

                cards[x][y] = c;
            }
        }


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Config.CARD_WIDTH = (Math.min(w, h) - 12) / 4;

        addCard(Config.CARD_WIDTH, Config.CARD_WIDTH);
        startGame();

    }

    public void startGame() {
        MainActivity.getMainActivity().getBestScore();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                cards[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }


    private void addRandomNum() {
        points.clear();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (cards[x][y].getNum() == 0)
                    points.add(new Point(x, y));
            }
        }
        if (points.size() > 0) {
            Point p = points.remove((int) (Math.random() * points.size()));
            cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
            MainActivity.getMainActivity().getAnimLayer().createScaleTo1(cards[p.x][p.y]);
        }

    }


}
