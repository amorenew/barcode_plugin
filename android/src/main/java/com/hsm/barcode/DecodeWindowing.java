package com.hsm.barcode;

public class DecodeWindowing {

    public static final class DecodeWindow {
        public int LowerRightX;
        public int LowerRightY;
        public int UpperLeftX;
        public int UpperLeftY;
    }

    public static final class DecodeWindowLimits {
        public int LowerRight_X_Max;
        public int LowerRight_X_Min;
        public int LowerRight_Y_Max;
        public int LowerRight_Y_Min;
        public int UpperLeft_X_Max;
        public int UpperLeft_X_Min;
        public int UpperLeft_Y_Max;
        public int UpperLeft_Y_Min;
    }

    public static final class DecodeWindowMode {
        public static final int DECODE_WINDOW_MODE_AIMER = 1;
        public static final int DECODE_WINDOW_MODE_DISABLED = 0;
        public static final int DECODE_WINDOW_MODE_FIELD_OF_VIEW = 2;
        public static final int DECODE_WINDOW_MODE_SUB_IMAGE = 3;

        private DecodeWindowMode() {
        }
    }

    public static final class DecodeWindowShowWindow {
        public static final int DECODE_WINDOW_SHOW_WINDOW_BLACK = 2;
        public static final int DECODE_WINDOW_SHOW_WINDOW_DISABLED = 0;
        public static final int DECODE_WINDOW_SHOW_WINDOW_WHITE = 1;

        private DecodeWindowShowWindow() {
        }
    }
}
