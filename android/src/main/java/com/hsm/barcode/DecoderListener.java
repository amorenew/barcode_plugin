package com.hsm.barcode;

public interface DecoderListener {
    boolean onKeepGoingCallback();

    boolean onMultiReadCallback();
}
