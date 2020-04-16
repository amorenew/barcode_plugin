package com.scan.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hsm.barcode.DecoderConfigValues;
import com.scan.service.config.ScanConfig;

public class BootReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    ScanConfig config;

    public void onReceive(Context context, Intent intent) {
        this.config = new ScanConfig(context);
        if (intent.getAction().equals(ACTION) && this.config.isOpen()) {
            Intent intent1 = new Intent(context, FloatWindow.class);
            intent1.addFlags(DecoderConfigValues.SymbologyFlags.SYMBOLOGY_POSICODE_LIMITED_2);
            context.startService(intent1);
        }
    }
}
