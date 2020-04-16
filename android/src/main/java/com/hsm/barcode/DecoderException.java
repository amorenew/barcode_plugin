package com.hsm.barcode;

public class DecoderException extends Exception {
    private static final long serialVersionUID = 1;
    private int errorCode;

    public DecoderException(int code, String message) {
        super(message);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public static final class ResultID {
        public static final int RESULT_ERR_BADREGION = 1;
        public static final int RESULT_ERR_BADSMARTIMAGE = 12;
        public static final int RESULT_ERR_CANCEL = 17;
        public static final int RESULT_ERR_DRIVER = 2;
        public static final int RESULT_ERR_ENGINEBUSY = 3;
        public static final int RESULT_ERR_EXCEPTION = 18;
        public static final int RESULT_ERR_EXMFILE_INVALID = 21;
        public static final int RESULT_ERR_LOAD_EXMFILE = 20;
        public static final int RESULT_ERR_MEMORY = 4;
        public static final int RESULT_ERR_MISSING_EXMSECTION = 22;
        public static final int RESULT_ERR_NODATA = 24;
        public static final int RESULT_ERR_NODECODE = 5;
        public static final int RESULT_ERR_NOIMAGE = 6;
        public static final int RESULT_ERR_NORESPONSE = 7;
        public static final int RESULT_ERR_NOTCONNECTED = 8;
        public static final int RESULT_ERR_NOTRIGGER = 11;
        public static final int RESULT_ERR_PARAMETER = 9;
        public static final int RESULT_ERR_PROCESSING_EXMSECTION = 23;
        public static final int RESULT_ERR_SMARTIMAGETOOLARGE = 13;
        public static final int RESULT_ERR_THREAD = 16;
        public static final int RESULT_ERR_TOO_MUCH_INTERPOLATION = 14;
        public static final int RESULT_ERR_UNSUPPORTED = 10;
        public static final int RESULT_ERR_UNSUPPORTED_IQ_BARCODE = 19;
        public static final int RESULT_ERR_WRONGRESULTSTRUCT = 15;
        public static final int RESULT_INITIALIZE = -1;
        public static final int RESULT_SUCCESS = 0;

        private ResultID() {
        }
    }
}
