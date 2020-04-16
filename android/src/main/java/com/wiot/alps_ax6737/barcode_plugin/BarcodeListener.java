package com.wiot.alps_ax6737.barcode_plugin;

public interface BarcodeListener {
    void onResult(int code, String barcode);
}
