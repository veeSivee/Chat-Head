package com.tes.vi.chathead;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tes.vi.chathead.movechathead.chatHeadPresenterImpl;

public class ChatService extends Service {

    private String sMsg = "";
    private WindowManager windowManager;
    private ImageView ivChatHead, ivRemove;
    private RelativeLayout rlChatHead, rlRemove;
    private Point szWindow = new Point();

    private chatHeadPresenterImpl mChatHeadPresenter;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mChatHeadPresenter = null;
        if(startId == Service.START_STICKY) {
            handleStart();
            return super.onStartCommand(intent, flags, startId);
        }else{
            return  Service.START_NOT_STICKY;
        }
    }

    private void handleStart(){

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        rlRemove = (RelativeLayout)inflater.inflate(R.layout.remove,null);
        ivRemove = (ImageView)rlRemove.findViewById(R.id.ivRemove);
        WindowManager.LayoutParams paramRemove = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        paramRemove.gravity = Gravity.TOP | Gravity.LEFT;
        rlRemove.setVisibility(View.GONE);
        windowManager.addView(rlRemove, paramRemove);


        rlChatHead = (RelativeLayout) inflater.inflate(R.layout.chathead, null);
        ivChatHead = (ImageView)rlChatHead.findViewById(R.id.ivChathead);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            windowManager.getDefaultDisplay().getSize(szWindow);
        } else {
            int w = windowManager.getDefaultDisplay().getWidth();
            int h = windowManager.getDefaultDisplay().getHeight();
            szWindow.set(w, h);
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(rlChatHead, params);
        //myHandler.postDelayed(myRunnable, 4000);

        chatHeadTouch();
    }

    private void chatHeadTouch(){
        rlChatHead.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) rlChatHead.getLayoutParams();

                int x_cord = (int) event.getRawX();
                int y_cord = (int) event.getRawY();
                int x_cord_Destination, y_cord_Destination;

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mChatHeadPresenter.buttonMoveDown(x_cord, y_cord);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    default:
                        break;
                }

                return true;
            }
        });


        rlChatHead.setOnClickListener(new View.OnClickListener() {

            Handler handler_longClick = new Handler();
            Runnable runnable_longClick = new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    chathead_longclick();
                    rlRemove.setVisibility(View.VISIBLE);
                }
            };

            @Override
            public void onClick(View view) {
                handler_longClick.removeCallbacks(runnable_longClick);
            }
        });
    }


    Handler myHandler = new Handler();
    Runnable myRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

        }
    };

    private int getStatusBarHeight() {
        int statusBarHeight = (int) Math.ceil(25 * getApplicationContext().getResources().getDisplayMetrics().density);
        return statusBarHeight;
    }

    private void chathead_longclick(){

        WindowManager.LayoutParams param_remove = (WindowManager.LayoutParams) rlRemove.getLayoutParams();
        int x_cord_remove = (szWindow.x - rlRemove.getWidth()) / 2;
        int y_cord_remove = szWindow.y - (rlRemove.getHeight() + getStatusBarHeight() );

        param_remove.x = x_cord_remove;
        param_remove.y = y_cord_remove;

        windowManager.updateViewLayout(rlRemove, param_remove);
    }
}
