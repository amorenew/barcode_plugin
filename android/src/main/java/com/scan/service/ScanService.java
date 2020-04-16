package com.scan.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.hsm.barcode.DecodeResult;
import com.hsm.barcode.Decoder;
import com.hsm.barcode.DecoderException;
import com.hsm.barcode.SymbologyConfig;
import com.scan.service.IScan;
import com.scan.service.config.ScanConfig;
import com.scan.service.util.Tools;
import com.scan.service.util.Util;
import com.wiot.alps_ax6737.barcode_plugin.BarcodeHelper;

public class ScanService extends Service {
    public static final String ACTION_CLOSE_SCAN = "colseScan";
    public static final String ACTION_START_SCAN = "startScan";
    private static final int INIT_FAIL = -1;
    private static final int INIT_OK = 1;
    private final int MSG_SCAN = 1009;
    private String TAG = "ScanService";
    /* access modifiers changed from: private */
    public AidlIscan aidlScan = new AidlIscan();
    /* access modifiers changed from: private */
    public String charSetName = "utf-8";
    private boolean isOpen;
    boolean isOpening = false;
    private KillReceiver killReceiver;
    /* access modifiers changed from: private */
    public DecodeResult mDecodeResult;
    /* access modifiers changed from: private */
    public Decoder mDecoder;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            ScanService.this.prefixStr = ScanService.this.scanConfig.getPrefix();
            ScanService.this.surfixStr = ScanService.this.scanConfig.getSurfix();
            BarcodeHelper.getInstance().getBarcodeListener().onResult(msg.what,"");

            if (msg.what == 1009) {
                ScanService.this.sendToInput(ScanService.this.prefixStr, false);
                ScanService.this.sendToInput(ScanService.this.result, false);
                Log.e("surfix -- ", "tab = " + ScanService.this.scanConfig.isTab() + ";  enter = " + ScanService.this.scanConfig.isEnter());
                ScanService.this.sendToInput(ScanService.this.surfixStr, false);
                if (ScanService.this.scanConfig.isTab()) {
                    ScanService.this.sendToInput("\t", false);
                }
                if (ScanService.this.scanConfig.isEnter()) {
                    ScanService.this.sendToInput("\n", true);
                }
                if (ScanService.this.scanConfig.isVoice()) {
                  //  Util.play(1, 0);
                }
            }
        }
    };
    private boolean othreRequest = false;
    /* access modifiers changed from: private */
    public String prefixStr;
    /* access modifiers changed from: private */
    public String result = "";
    /* access modifiers changed from: private */
    public boolean running = false;
    /* access modifiers changed from: private */
    public ScanConfig scanConfig;
    /* access modifiers changed from: private */
    public boolean scanning = false;
    private long startTime = 0;
    /* access modifiers changed from: private */
    public String surfixStr;

    /* access modifiers changed from: private */
    public void sendToInput(String data, boolean enterFlag) {
        Intent toBack = new Intent();
        toBack.setAction("android.rfid.INPUT");
        toBack.putExtra("data", data);
        toBack.putExtra("enter", enterFlag);
        sendBroadcast(toBack);
    }

    public IBinder onBind(Intent intent) {
        this.othreRequest = true;
        return this.aidlScan;
    }

    public void onCreate() {
        this.scanConfig = new ScanConfig(this);
        this.killReceiver = new KillReceiver(this, (KillReceiver) null);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CLOSE_SCAN);
        filter.addAction(ACTION_START_SCAN);
        registerReceiver(this.killReceiver, filter);
        super.onCreate();
    }

    public void unbindService(ServiceConnection conn) {
        this.othreRequest = false;
        super.unbindService(conn);
    }

    public void onDestroy() {
        if (this.killReceiver != null) {
            unregisterReceiver(this.killReceiver);
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.othreRequest) {
            synchronized (this.aidlScan) {
                try {
                    if (!this.running) {
                        this.running = true;
                        this.scanning = false;
                        this.aidlScan.init();
                        this.aidlScan.setOnResultListener(new IScanResult() {
                            public IBinder asBinder() {
                                return null;
                            }

                            public void onListener(String barcode) throws RemoteException {
                                ScanService.this.result = barcode;
                                Message msg = new Message();
                                msg.what = 1009;
                                Bundle bundle = new Bundle();
                                bundle.putString("data", ScanService.this.result);
                                msg.setData(bundle);
                                ScanService.this.mHandler.sendMessage(msg);
                            }
                        });
                    } else {
                        this.startTime = System.currentTimeMillis();
                        this.aidlScan.scan();
                    }
                } catch (Exception e) {
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class AidlIscan extends IScan.Stub {
        /* access modifiers changed from: private */
        public IScanResult iResultLister;
        private Runnable scanRun = new Runnable() {
            public synchronized void run() {
                if (ScanService.this.mDecoder != null) {
                    ScanService.this.scanning = true;
                    try {
                        boolean callbackKeepGoing = ScanService.this.mDecoder.callbackKeepGoing();
                        Thread.sleep(50);
                        ScanService.this.mDecoder.waitForDecode(5000);
                        if (!(ScanService.this.mDecoder.getBarcodeData() == null || ScanService.this.mDecoder.getBarcodeLength() <= 0 || AidlIscan.this.iResultLister == null)) {
                            Log.e("barcode", "barcode = " + ScanService.this.mDecoder.getBarcodeData());
                            byte[] barCode = ScanService.this.mDecoder.getBarcodeByteData();
                            String Bytes2HexString = Tools.Bytes2HexString(barCode, barCode.length);
                            AidlIscan.this.iResultLister.onListener(new String(barCode));
                        }
                        Thread.sleep(50);
                        ScanService.this.scanning = false;
                    } catch (DecoderException e) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                        ScanService.this.scanning = false;
                    } catch (RemoteException e2) {
                        ScanService.this.scanning = false;
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
                return;
            }
        };
        private Thread scanThread = null;

        AidlIscan() {
        }

        public int init() throws RemoteException {
            ScanService.this.mDecoder = new Decoder();
            ScanService.this.mDecodeResult = new DecodeResult();
            try {
                ScanService.this.mDecoder.connectDecoderLibrary();
                ScanService.this.mDecoder.enableSymbology(17);
                ScanService.this.mDecoder.enableSymbology(15);
                ScanService.this.mDecoder.enableSymbology(10);
                ScanService.this.mDecoder.enableSymbology(3);
                ScanService.this.mDecoder.enableSymbology(8);
                SymbologyConfig config = new SymbologyConfig(10);
                config.Flags = 5;
                config.Mask = 1;
                ScanService.this.mDecoder.setSymbologyConfig(config);
                try {
                    ScanService.this.mDecoder.startScanning();
                    Thread.sleep(50);
                    ScanService.this.mDecoder.stopScanning();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 1;
                }
            } catch (DecoderException e2) {
                e2.printStackTrace();
                return -1;
            }
        }

        public void close() throws RemoteException {
            ScanService.this.running = false;
            if (ScanService.this.mDecoder != null) {
                try {
                    ScanService.this.mDecoder.disconnectDecoderLibrary();
                    ScanService.this.mDecoder = null;
                } catch (DecoderException e) {
                    e.printStackTrace();
                }
            }
        }

        public void scan() throws RemoteException {
            if (!ScanService.this.scanning) {
                if (this.scanThread != null) {
                    this.scanThread.interrupt();
                    this.scanThread = null;
                }
                this.scanThread = new Thread(this.scanRun);
                this.scanThread.start();
            }
        }

        public void setOnResultListener(IScanResult iLister) throws RemoteException {
            this.iResultLister = iLister;
        }

        public void setChar(String charSetName) throws RemoteException {
            ScanService.this.charSetName = charSetName;
        }
    }

    private class KillReceiver extends BroadcastReceiver {
        private KillReceiver() {
        }

        /* synthetic */ KillReceiver(ScanService scanService, KillReceiver killReceiver) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals(ScanService.ACTION_CLOSE_SCAN) && ScanService.this.aidlScan != null) {
                    ScanService.this.aidlScan.close();
                    ScanService.this.running = false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
