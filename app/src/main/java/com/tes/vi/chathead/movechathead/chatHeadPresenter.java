package com.tes.vi.chathead.movechathead;

import android.graphics.Point;
import android.widget.ImageView;

/**
 * Created by taufiqotulfaidah on 9/22/16.
 */
public interface chatHeadPresenter {

    void buttonMove(boolean isLongclick, Point szWindow, ImageView removeImg);

    void buttonMoveUp();

    void buttonMoveDown(int x_cord, int y_cord,ImageView removeImg);

    void start();

}
