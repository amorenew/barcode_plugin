package com.scan.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.scan.service.config.ScanConfig;
import com.scan.service.util.Util;
import com.wiot.alps_ax6737.barcode_plugin.BarcodeHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FloatWindow extends Service {
    //private static final String KEY_UHF = "uhf";
    //private static final int MSG_UHF = 1102;
    //Button btnScan;
    /* access modifiers changed from: private */
    //public Set<String> epcSet = null;
    /*private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FloatWindow.MSG_UHF:
                    Log.e("msg_epc", msg.getData().getString(FloatWindow.KEY_UHF));
                    return;
                default:
                    return;
            }
        }
    };*/
    //private Set<String> hashSetEpc = new HashSet();
    LinearLayout mFloatLayout;
    /* access modifiers changed from: private */
    public float mStartX;
    /* access modifiers changed from: private */
    public float mStartY;
    /* access modifiers changed from: private */
    public float mTouchX;
    /* access modifiers changed from: private */
    public float mTouchY;
    //private int port = 12;
    private MReceiver receiver;
    /* access modifiers changed from: private */
    public boolean runFlag = true;
    /* access modifiers changed from: private */
    //public ScanConfig scanConfig;
    //private String serialportPath = "/dev/ttyMT1";
    /* access modifiers changed from: private */
    public boolean startFlag = false;
    private WindowManager wm;
    WindowManager.LayoutParams wmParams;
    /* access modifiers changed from: private */
    public float x;
    /* access modifiers changed from: private */
    public float y;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        //Util.initSoundPool(this);
        createFloatWindow();
        initReceiver();
        // this.scanConfig = new ScanConfig(this);
        this.runFlag = true;
        this.startFlag = false;
        Log.e("FloatWindow", "is1D = " + BarcodeHelper.getInstance().is1DimenModule());
        if (BarcodeHelper.getInstance().is1DimenModule()) {
            startService(new Intent(this, Scan1DService.class));
        } else {
            startService(new Intent(this, ScanService.class));
        }
        super.onCreate();
    }

    public void onDestroy() {
        this.wm.removeView(this.mFloatLayout);
        this.runFlag = false;
        this.startFlag = false;
        if (this.receiver != null) {
            unregisterReceiver(this.receiver);
        }
        super.onDestroy();
    }

    private void createFloatWindow() {
        this.wmParams = new WindowManager.LayoutParams();
        this.wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        Display display = this.wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        this.wmParams.type = 2002;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 51;
        int LAYOUT_FLAG;
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        this.wmParams.flags=
                LAYOUT_FLAG;

        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.wmParams=new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        this.wmParams.gravity=Gravity.CENTER| Gravity.CENTER;*/
        this.wmParams.x = dm.widthPixels;
        this.wmParams.y = dm.heightPixels / 2;
        this.wmParams.width = 60;
        this.wmParams.height = 60;


       // this.mFloatLayout = (LinearLayout) LayoutInflater.from(getApplication()).inflate(R.layout.float_window, (ViewGroup) null);
        this.mFloatLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                FloatWindow.this.x = event.getRawX();
                FloatWindow.this.y = event.getRawY();
                switch (event.getAction()) {
                    case 0:
                        Log.e("", "ACTION_DOWN");
                        FloatWindow.this.mTouchX = event.getX();
                        FloatWindow.this.mTouchY = event.getY();
                        FloatWindow.this.mStartX = FloatWindow.this.x;
                        FloatWindow.this.mStartY = FloatWindow.this.y;
                    //    FloatWindow.this.mFloatLayout.setBackground(FloatWindow.this.getResources().getDrawable(R.drawable.bg_close));
                        return true;
                    case 1:
                    //    FloatWindow.this.mFloatLayout.setBackground(FloatWindow.this.getResources().getDrawable(R.drawable.bg));
                        if (FloatWindow.this.x - FloatWindow.this.mStartX >= 5.0f || FloatWindow.this.y - FloatWindow.this.mStartY >= 5.0f) {
                            Log.e("onclice", "finish++++");
                            FloatWindow.this.updateView();
                       //     FloatWindow.this.mFloatLayout.setBackground(FloatWindow.this.getResources().getDrawable(R.drawable.bg));
                            FloatWindow floatWindow = FloatWindow.this;
                            FloatWindow.this.mTouchY = 0.0f;
                            floatWindow.mTouchX = 0.0f;
                            return true;
                        }
                        Log.e("onclice", "start = " + FloatWindow.this.startFlag);
                        BarcodeHelper.getInstance().startScanning();
                        return true;
                    case 2:
                        if (FloatWindow.this.x - FloatWindow.this.mStartX < 5.0f) {
                        }
                        return true;
                    default:
                        return true;
                }
            }
        });
        this.wm.addView(this.mFloatLayout, this.wmParams);
    }


    /* access modifiers changed from: private */
    public void updateView() {
        this.wmParams.x = (int) (this.x - this.mTouchX);
        this.wmParams.y = (int) (this.y - this.mTouchY);
        this.wm.updateViewLayout(this.mFloatLayout, this.wmParams);
    }

    private void initReceiver() {
        this.receiver = new MReceiver(this, (MReceiver) null);
        registerReceiver(this.receiver, new IntentFilter("com.scan.service.FloatWindow"));
    }

    private class MReceiver extends BroadcastReceiver {
        private MReceiver() {
        }

        /* synthetic */ MReceiver(FloatWindow floatWindow, MReceiver mReceiver) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            FloatWindow.this.stopSelf();
        }
    }

/*    class InventoryThread extends Thread {
        private List<String> epcList;

        InventoryThread() {
        }

        public void run() {
            super.run();
            Log.e("InventoryThread\t", "start()");
            while (FloatWindow.this.runFlag) {
                if (FloatWindow.this.startFlag) {
                    if (this.epcList != null && !this.epcList.isEmpty()) {
                        for (String epc : this.epcList) {
                            Log.e("epc\t", epc);
                            if (FloatWindow.this.epcSet.isEmpty() || !FloatWindow.this.epcSet.contains(epc)) {
                                FloatWindow.this.epcSet.add(epc);
                                FloatWindow.this.sendMSG(epc);
                            }
                        }
                    }
                    this.epcList = null;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
*/
    /* access modifiers changed from: private */
 /*   public void sendMSG(String msg) {
//        Util.play(1, 0);
        Message message = new Message();
        message.what = MSG_UHF;
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UHF, msg);
        message.setData(bundle);
        this.handler.sendMessage(message);
    }*/
}
