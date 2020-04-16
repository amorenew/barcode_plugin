package com.scan.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import cn.pda.scan.ScanThread;
import com.scan.service.config.ScanConfig;
import com.scan.service.util.Util;
import com.wiot.alps_ax6737.barcode_plugin.BarcodeHelper;
import com.wiot.alps_ax6737.barcode_plugin.BarcodeListener;

import java.io.IOException;

public class Scan1DService extends Service {
    /* access modifiers changed from: private */
    public String TAG = "Scan1DService";
    private BroadcastReceiver killReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("kill", false)) {
                if (Scan1DService.this.scan != null) {
                    Scan1DService.this.scan.close();
                    Scan1DService.this.scan = null;
                }
                Scan1DService.this.stopSelf();
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Scan1DService.this.prefixStr = Scan1DService.this.scanConfig.getPrefix();
            Scan1DService.this.surfixStr = Scan1DService.this.scanConfig.getSurfix();
            Log.e(Scan1DService.this.TAG, "handleMessage");
            Log.e(Scan1DService.this.TAG,"Code: "+msg.what);
            // Toast.makeText(Scan1DService.this,"Result Code: "+msg.what,Toast.LENGTH_LONG).show();
            String data="";
            if (msg.what == 1001) {
                data = msg.getData().getString("data");

                Log.e(Scan1DService.this.TAG, data);
                Scan1DService.this.sendToInput(Scan1DService.this.prefixStr, false);
                Scan1DService.this.sendToInput(data, false);
                Scan1DService.this.sendToInput(Scan1DService.this.surfixStr, false);
                Log.e("surfix -- ", "tab = " + Scan1DService.this.scanConfig.isTab() + ";  enter = " + Scan1DService.this.scanConfig.isEnter());
                Scan1DService.this.sendToInput(Scan1DService.this.surfixStr, false);
                if (Scan1DService.this.scanConfig.isTab()) {
                    Scan1DService.this.sendToInput("\t", false);
                }
                if (Scan1DService.this.scanConfig.isEnter()) {
                    Scan1DService.this.sendToInput("", true);
                }
                if (Scan1DService.this.scanConfig.isVoice()) {
                    // Util.play(1, 0);
                }
            }
            BarcodeHelper.getInstance().getBarcodeListener().onResult(msg.what,data);
        }
    };
    /* access modifiers changed from: private */
    public String prefixStr;
    /* access modifiers changed from: private */
    public ScanThread scan = null;
    /* access modifiers changed from: private */
    public ScanConfig scanConfig;
    /* access modifiers changed from: private */
    public String surfixStr;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.scanConfig = new ScanConfig(this);
        //Util.initSoundPool(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.KILL_SERVER");
        registerReceiver(this.killReceiver, filter);
        super.onCreate();
    }

    public void onDestroy() {
        unregisterReceiver(this.killReceiver);
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(this.TAG, "+++ onstart command++++");
        if (this.scan == null) {
            try {
                this.scan = new ScanThread(this.mHandler);
                this.scan.start();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            this.scan.scan();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /* access modifiers changed from: private */
    public void sendToInput(String data, boolean enterFlag) {
        Intent toBack = new Intent();
        toBack.setAction("android.rfid.INPUT");
        toBack.putExtra("data", data);
        toBack.putExtra("enter", enterFlag);
        sendBroadcast(toBack);
    }
}
