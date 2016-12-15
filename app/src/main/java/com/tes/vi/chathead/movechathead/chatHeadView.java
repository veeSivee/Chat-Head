package com.tes.vi.chathead.movechathead;

import android.view.WindowManager;

/**
 * Created by taufiqotulfaidah on 9/22/16.
 */
public interface chatHeadView {

    void showRemoveButton();

    void showMessage();

    void UpdateViewLayout(int layoutID,WindowManager.LayoutParams param);

    void startChatHead();
}
