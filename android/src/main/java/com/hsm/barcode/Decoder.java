package com.hsm.barcode;

import android.graphics.Bitmap;

public class Decoder {
    private static final String TAG = "Decoder.java";
    private DecoderListener observer;

    public native void connectDecoderLibrary() throws DecoderException;

    public native void disableSymbology(int i) throws DecoderException;

    public native void disconnectDecoderLibrary() throws DecoderException;

    public native void enableSymbology(int i) throws DecoderException;

    public native String getAPIRevision() throws DecoderException;

    public native byte getBarcodeAimID() throws DecoderException;

    public native byte getBarcodeAimModifier() throws DecoderException;

    public native byte[] getBarcodeByteData() throws DecoderException;

    public native byte getBarcodeCodeID() throws DecoderException;

    public native String getBarcodeData() throws DecoderException;

    public native int getBarcodeLength() throws DecoderException;

    public native String getControlLogicRevision() throws DecoderException;

    public native String getDecThreadsRevision() throws DecoderException;

    public native void getDecodeOptions(DecodeOptions decodeOptions) throws DecoderException;

    public native void getDecodeWindow(DecodeWindowing.DecodeWindow decodeWindow) throws DecoderException;

    public native void getDecodeWindowLimits(DecodeWindowing.DecodeWindowLimits decodeWindowLimits) throws DecoderException;

    public native int getDecodeWindowMode() throws DecoderException;

    public native String getDecoderRevision() throws DecoderException;

    public native int getEngineID() throws DecoderException;

    public native String getEngineSerialNumber() throws DecoderException;

    public native int getEngineType() throws DecoderException;

    public native String getErrorMessage(int i) throws DecoderException;

    public native int getExposureMode() throws DecoderException;

    public native void getExposureSettings(int[] iArr) throws DecoderException;

    public native void getIQImage(IQImagingProperties iQImagingProperties, Bitmap bitmap) throws DecoderException;

    public native int getImageHeight() throws DecoderException;

    public native int getImageWidth() throws DecoderException;

    public native void getImagerProperties(ImagerProperties imagerProperties) throws DecoderException;

    public native int getLastDecodeTime() throws DecoderException;

    public native byte[] getLastImage(ImageAttributes imageAttributes) throws DecoderException;

    public native int getLightsMode() throws DecoderException;

    public native int getMaxMessageLength() throws DecoderException;

    public native int getOCRMode() throws DecoderException;

    public native int getOCRTemplates() throws DecoderException;

    public native byte[] getOCRUserTemplate() throws DecoderException;

    public native int getPSOCMajorRev() throws DecoderException;

    public native int getPSOCMinorRev() throws DecoderException;

    public native void getPreviewFrame(Bitmap bitmap) throws DecoderException;

    public native String getScanDriverRevision() throws DecoderException;

    public native String getSecondaryDecoderRevision() throws DecoderException;

    public native int getShowDecodeWindow() throws DecoderException;

    public native void getSingleFrame(Bitmap bitmap) throws DecoderException;

    public native void getSymbologyConfig(SymbologyConfig symbologyConfig) throws DecoderException;

    public native void getSymbologyConfigDefaults(SymbologyConfig symbologyConfig) throws DecoderException;

    public native int getSymbologyMaxRange(int i) throws DecoderException;

    public native int getSymbologyMinRange(int i) throws DecoderException;

    public native void setDecodeAttemptLimit(int i) throws DecoderException;

    public native void setDecodeOptions(DecodeOptions decodeOptions) throws DecoderException;

    public native void setDecodeWindow(DecodeWindowing.DecodeWindow decodeWindow) throws DecoderException;

    public native void setDecodeWindowMode(int i) throws DecoderException;

    public native void setExposureMode(int i) throws DecoderException;

    public native void setExposureSettings(int[] iArr) throws DecoderException;

    public native void setLastImage(byte[] bArr, int i, int i2) throws DecoderException;

    public native void setLightsMode(int i) throws DecoderException;

    public native void setOCRMode(int i) throws DecoderException;

    public native void setOCRTemplates(int i) throws DecoderException;

    public native void setOCRUserTemplate(byte[] bArr) throws DecoderException;

    public native void setShowDecodeWindow(int i) throws DecoderException;

    public native void setSymbologyConfig(SymbologyConfig symbologyConfig) throws DecoderException;

    public native void setSymbologyDefaults(int i) throws DecoderException;

    public native void startScanning() throws DecoderException;

    public native void stopScanning() throws DecoderException;

    public native void waitForDecode(int i) throws DecoderException;

    public native void waitForDecodeTwo(int i, DecodeResult decodeResult) throws DecoderException;

    public native void waitMultipleDecode(int i) throws DecoderException;

    static {
        try {
            System.loadLibrary("HsmKil");
            System.loadLibrary("HHPScanInterface");
            System.loadLibrary("HSMDecoderAPI");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean callbackKeepGoing() {
        if (this.observer != null) {
            return this.observer.onKeepGoingCallback();
        }
        return true;
    }

    public boolean callbackMultiRead() {
        if (this.observer != null) {
            return this.observer.onMultiReadCallback();
        }
        return false;
    }

    public void setDecoderListeners(DecoderListener observer2) {
        this.observer = observer2;
    }
}
