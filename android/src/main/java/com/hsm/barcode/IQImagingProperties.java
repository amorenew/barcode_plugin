package com.hsm.barcode;

public class IQImagingProperties {
    public int AspectRatio;
    public int Format;
    public int Height;
    public int Reserved;
    public int Resolution;
    public int Width;
    public int X_Offset;
    public int Y_Offset;

    public static final class IQImageFormat {
        public static final int RAW_BINARY = 0;
        public static final int RAW_GRAY = 1;

        private IQImageFormat() {
        }
    }
}
