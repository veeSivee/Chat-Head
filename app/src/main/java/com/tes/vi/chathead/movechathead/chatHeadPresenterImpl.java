package com.tes.vi.chathead.movechathead;

import android.graphics.Point;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by taufiqotulfaidah on 9/22/16.
 */
public class chatHeadPresenterImpl implements chatHeadPresenter {

    private chatHeadView mChatHeadView;

    long time_start, time_end;
    boolean isLongclick, inBounded;
    int remove_img_width, remove_img_height;

    private int x_init_cord, y_init_cord, x_init_margin, y_init_margin;

    WindowManager.LayoutParams layoutParams;

    public static int RELATIVE_LAYOUT_REMOVE = 1;
    public static int RELATIVE_LAYOUT_CHATHEAD = 2;

    public chatHeadPresenterImpl(chatHeadView chatHeadView, ImageView removeImg,WindowManager.LayoutParams layoutParams){
        mChatHeadView = chatHeadView;
        this.layoutParams = layoutParams;

        time_start = 0;
        time_end = 0;
        isLongclick = false;
        inBounded = false;
        remove_img_width = 0;
        remove_img_height = 0;

    }

    @Override
    public void buttonMove(boolean isLongclick, Point szWindow, ImageView removeImg) {

    }

    @Override
    public void buttonMoveUp() {

    }

    @Override
    public void buttonMoveDown(int x_cord, int y_cord,ImageView removeImg) {

        time_start = System.currentTimeMillis();
        //handler_longClick.postDelayed(runnable_longClick, 600);

        remove_img_width = removeImg.getLayoutParams().width;
        remove_img_height = removeImg.getLayoutParams().height;

        x_init_cord = x_cord;
        y_init_cord = y_cord;

        x_init_margin = layoutParams.x;
        y_init_margin = layoutParams.y;
    }

    @Override
    public void start() {
        mChatHeadView.startChatHead();
    }
}
