package com.wiot.alps_ax6737.barcode_plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.scan.service.Scan1DService;
import com.scan.service.ScanService;
import com.scan.service.config.ScanConfig;

import java.util.Timer;
import java.util.TimerTask;

public class BarcodeHelper {
    private ScanConfig scanConfig;
    private Context context;
    private static BarcodeHelper instance;
    private BarcodeListener barcodeListener;

    private BarcodeHelper() {}

    public static BarcodeHelper getInstance() {
        if (instance == null)
            instance = new BarcodeHelper();
        return instance;
    }

    void init(Context context) {
        this.context = context;
        this.scanConfig = new ScanConfig(this.context);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1003) {
                //MainActivity.this.dialogLoading.cancel();
                scanConfig.setOpen(true);
            }
        }
    };


    //there is 1 dimension module and 2d barcode
    //2d is not working
    public boolean is1DimenModule() {
        return scanConfig.is1D();
    }

    void set1DimenModule(boolean enable) {
        scanConfig.set1D(enable);
    }


    boolean isOpen() {
        return scanConfig.isOpen();
    }

    public void startScanning() {
        if (is1DimenModule())
            context.startService(new Intent(context, Scan1DService.class));
        else
            context.startService(new Intent(context, ScanService.class));
    }

    void open() {
        scanConfig.setOpen(true);
    }

    void close() {
        Intent toKill = new Intent();
        toKill.setAction(ScanService.ACTION_CLOSE_SCAN);
        toKill.putExtra("kill", true);
        context.sendBroadcast(toKill);
        scanConfig.setOpen(false);
    }

    boolean isVoice() {
        return scanConfig.isVoice();
    }

    void setVoice(boolean enable) {
        scanConfig.setVoice(enable);
    }


    boolean isTab() {
        return scanConfig.isTab();
    }

    void setTab(boolean enable) {
        scanConfig.setTab(enable);
    }


    boolean isEnter() {
        return scanConfig.isEnter();
    }

    void setEnter(boolean enable) {
        scanConfig.setEnter(enable);
    }

    void setPrefix(String text) {
        scanConfig.setPrefix(text);
    }

    void setSurfix(String text) {
        scanConfig.setSurfix(text);
    }


    void initTimeOut() {
        // every 3500 milliseconds timeout code will be sent
        new Timer().schedule(new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.what = 1003;
                mHandler.sendMessage(msg);
            }
        }, 3500);

    }

    private final int CodeSuccess = 1001;
    private final int CodeFailed = 1002;
    private final int CodeTimeout = 1003;

   /* @Override
    public void onResult(int code, String barcode) {
        if (code == CodeSuccess) {
            Toast.makeText(context, "Result Code: Success", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Barcode: " + barcode, Toast.LENGTH_LONG).show();
        } else if (code == CodeFailed) {
            Toast.makeText(context, "Result Code: Failed", Toast.LENGTH_LONG).show();
        } else if (code == CodeTimeout) {
            Toast.makeText(context, "Result Code: Timeout", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Result Code: " + code, Toast.LENGTH_LONG).show();
        }
    }*/

    public void setBarcodeListener(BarcodeListener barcodeListener) {
        this.barcodeListener=barcodeListener;
    }

    public BarcodeListener getBarcodeListener() {
        return this.barcodeListener;
    }
}
