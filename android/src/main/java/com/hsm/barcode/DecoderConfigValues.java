package com.hsm.barcode;

public final class DecoderConfigValues {
    private DecoderConfigValues() {
    }

    public static final class SymbologyID {
        public static final int SYMBOLOGIES = 52;
        public static final int SYM_ALL = 100;
        public static final int SYM_AUSPOST = 25;
        public static final int SYM_AZTEC = 0;
        public static final int SYM_BPO = 23;
        public static final int SYM_CANPOST = 24;
        public static final int SYM_CHINAPOST = 38;
        public static final int SYM_CODABAR = 1;
        public static final int SYM_CODABLOCK = 27;
        public static final int SYM_CODE11 = 2;
        public static final int SYM_CODE128 = 3;
        public static final int SYM_CODE16K = 41;
        public static final int SYM_CODE32 = 34;
        public static final int SYM_CODE39 = 4;
        public static final int SYM_CODE49 = 5;
        public static final int SYM_CODE93 = 6;
        public static final int SYM_COMPOSITE = 7;
        public static final int SYM_COUPONCODE = 43;
        public static final int SYM_DATAMATRIX = 8;
        public static final int SYM_DUTCHPOST = 30;
        public static final int SYM_EAN13 = 10;
        public static final int SYM_EAN8 = 9;
        public static final int SYM_GRIDMATRIX = 49;
        public static final int SYM_GS1_128 = 47;
        public static final int SYM_HANXIN = 48;
        public static final int SYM_IATA25 = 26;
        public static final int SYM_IDTAG = 45;
        public static final int SYM_INT25 = 11;
        public static final int SYM_ISBT = 22;
        public static final int SYM_JAPOST = 28;
        public static final int SYM_KOREAPOST = 39;
        public static final int SYM_LABEL = 46;
        public static final int SYM_MATRIX25 = 36;
        public static final int SYM_MAXICODE = 12;
        public static final int SYM_MICROPDF = 13;
        public static final int SYM_MSI = 31;
        public static final int SYM_OCR = 14;
        public static final int SYM_PDF417 = 15;
        public static final int SYM_PLANET = 29;
        public static final int SYM_PLESSEY = 37;
        public static final int SYM_POSICODE = 42;
        public static final int SYM_POSTALS = 50;
        public static final int SYM_POSTNET = 16;
        public static final int SYM_QR = 17;
        public static final int SYM_RSS = 18;
        public static final int SYM_STRT25 = 35;
        public static final int SYM_TELEPEN = 40;
        public static final int SYM_TLCODE39 = 32;
        public static final int SYM_TRIOPTIC = 33;
        public static final int SYM_UPCA = 19;
        public static final int SYM_UPCE0 = 20;
        public static final int SYM_UPCE1 = 21;
        public static final int SYM_USPS4CB = 44;
        public static final int SYM_US_POSTALS1 = 51;

        private SymbologyID() {
        }
    }

    public final class SymbologyFlags {
        public static final int SYMBOLOGY_128_APPEND = 524288;
        public static final int SYMBOLOGY_2_DIGIT_ADDENDA = 128;
        public static final int SYMBOLOGY_5_DIGIT_ADDENDA = 256;
        public static final int SYMBOLOGY_ADDENDA_REQUIRED = 512;
        public static final int SYMBOLOGY_ADDENDA_SEPARATOR = 1024;
        public static final int SYMBOLOGY_AUSTRALIAN_BAR_WIDTH = 65536;
        public static final int SYMBOLOGY_AUS_POST_ALPHANUMERIC_C_TABLE = 2097152;
        public static final int SYMBOLOGY_AUS_POST_COMBINATION_N_AND_C_TABLES = 4194304;
        public static final int SYMBOLOGY_AUS_POST_NUMERIC_N_TABLE = 1048576;
        public static final int SYMBOLOGY_CHECK_ENABLE = 2;
        public static final int SYMBOLOGY_CHECK_TRANSMIT = 4;
        public static final int SYMBOLOGY_CODABAR_CONCATENATE = 536870912;
        public static final int SYMBOLOGY_COMPOSITE_UPC = 8192;
        public static final int SYMBOLOGY_ENABLE = 1;
        public static final int SYMBOLOGY_ENABLE_APPEND_MODE = 16;
        public static final int SYMBOLOGY_ENABLE_FULLASCII = 32;
        public static final int SYMBOLOGY_EXPANDED_UPCE = 2048;
        public static final int SYMBOLOGY_NUM_SYS_TRANSMIT = 64;
        public static final int SYMBOLOGY_POSICODE_LIMITED_1 = 134217728;
        public static final int SYMBOLOGY_POSICODE_LIMITED_2 = 268435456;
        public static final int SYMBOLOGY_RSE_ENABLE = 8388608;
        public static final int SYMBOLOGY_RSL_ENABLE = 16777216;
        public static final int SYMBOLOGY_RSS_ENABLE = 33554432;
        public static final int SYMBOLOGY_RSX_ENABLE_MASK = 58720256;
        public static final int SYMBOLOGY_START_STOP_XMIT = 8;
        public static final int SYMBOLOGY_TELEPEN_OLD_STYLE = 67108864;
        public static final int SYMBOLOGY_UPCE1_ENABLE = 4096;
        public static final int SYM_MASK_ALL = 7;
        public static final int SYM_MASK_FLAGS = 1;
        public static final int SYM_MASK_MAX_LEN = 4;
        public static final int SYM_MASK_MIN_LEN = 2;

        private SymbologyFlags() {
        }
    }

    public static final class EngineID {
        public static final int IMAGER_4200_ENGINE = 1;
        public static final int IMAGER_IT4000_ENGINE = 5;
        public static final int IMAGER_IT4100_ENGINE = 6;
        public static final int IMAGER_IT4300_ENGINE = 7;
        public static final int IMAGER_IT5100_ENGINE = 8;
        public static final int IMAGER_IT5300_ENGINE = 9;
        public static final int IMAGER_N5600_ENGINE = 13;
        public static final int IMAGER_N5603_ENGINE = 12;
        public static final int LASER_SE1200_ENGINE = 2;
        public static final int LASER_SE1223_ENGINE = 3;
        public static final int NONE = 0;
        public static final int UNKNOWN = -1;

        private EngineID() {
        }
    }

    public static final class EngineType {
        public static final int IMAGER = 1;
        public static final int LASER = 2;
        public static final int NONE = 0;
        public static final int UNKNOWN = -1;

        private EngineType() {
        }
    }

    public static final class OCRMode {
        public static final int OCR_BOTH = 3;
        public static final int OCR_INVERSE = 2;
        public static final int OCR_NORMAL_VIDEO = 1;
        public static final int OCR_OFF = 0;

        private OCRMode() {
        }
    }

    public static final class OCRTemplate {
        public static final int ISBN = 4;
        public static final int MICRE13B = 16;
        public static final int PASSPORT = 2;
        public static final int PRICE_FIELD = 8;
        public static final int USER = 1;

        private OCRTemplate() {
        }
    }

    public static final class LightsMode {
        public static final int AIMER_ONLY = 1;
        public static final int CONCURRENT = 4;
        public static final int ILLUM_AIM_OFF = 0;
        public static final int ILLUM_AIM_ON = 3;
        public static final int ILLUM_ONLY = 2;

        private LightsMode() {
        }
    }
}
