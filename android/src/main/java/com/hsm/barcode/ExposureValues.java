package com.hsm.barcode;

public final class ExposureValues {

    public static final class ConformImage {
        public static final int CONFORM_IMAGE = 1;
        public static final int DO_NOT_CONFORM_IMAGE = 0;
    }

    public static final class ExposureMethod {
        public static final int CENTER_ONLY = 1;
        public static final int CENTER_WEIGHTED = 2;
        public static final int UNIFORM = 0;
    }

    public static final class ExposureMode {
        public static final int FIXED = 0;
        public static final int HHP = 2;
    }

    public static final class ExposureSettings {
        public static final int DEC_ES_CONFORM_IMAGE = 9;
        public static final int DEC_ES_CONFORM_TRIES = 10;
        public static final int DEC_ES_EXPOSURE_METHOD = 0;
        public static final int DEC_ES_FIXED_EXP = 14;
        public static final int DEC_ES_FIXED_FRAME_RATE = 16;
        public static final int DEC_ES_FIXED_GAIN = 15;
        public static final int DEC_ES_FRAME_RATE = 8;
        public static final int DEC_ES_MAX_EXP = 4;
        public static final int DEC_ES_MAX_GAIN = 5;
        public static final int DEC_ES_SPECULAR_EXCLUSION = 11;
        public static final int DEC_ES_SPECULAR_LIMIT = 13;
        public static final int DEC_ES_SPECULAR_SAT = 12;
        public static final int DEC_ES_TARGET_ACCEPT_GAP = 3;
        public static final int DEC_ES_TARGET_PERCENTILE = 2;
        public static final int DEC_ES_TARGET_VALUE = 1;
    }

    private ExposureValues() {
    }

    public class ExposureSettingsMinMax {
        public static final int DEC_MAX_ES_CONFORM_IMAGE = 1;
        public static final int DEC_MAX_ES_CONFORM_TRIES = 8;
        public static final int DEC_MAX_ES_EXPOSURE_METHOD = 2;
        public static final int DEC_MAX_ES_FIXED_EXP = 7874;
        public static final int DEC_MAX_ES_FIXED_FRAME_RATE = 30;
        public static final int DEC_MAX_ES_FIXED_GAIN = 4;
        public static final int DEC_MAX_ES_FRAME_RATE = 30;
        public static final int DEC_MAX_ES_MAX_EXP = 7874;
        public static final int DEC_MAX_ES_MAX_GAIN = 4;
        public static final int DEC_MAX_ES_SPECULAR_EXCLUSION = 4;
        public static final int DEC_MAX_ES_SPECULAR_LIMIT = 5;
        public static final int DEC_MAX_ES_SPECULAR_SAT = 255;
        public static final int DEC_MAX_ES_TARGET_ACCEPT_GAP = 50;
        public static final int DEC_MAX_ES_TARGET_PERCENTILE = 99;
        public static final int DEC_MAX_ES_TARGET_VALUE = 255;
        public static final int DEC_MIN_ES_CONFORM_IMAGE = 0;
        public static final int DEC_MIN_ES_CONFORM_TRIES = 1;
        public static final int DEC_MIN_ES_EXPOSURE_METHOD = 0;
        public static final int DEC_MIN_ES_FIXED_EXP = 1;
        public static final int DEC_MIN_ES_FIXED_FRAME_RATE = 1;
        public static final int DEC_MIN_ES_FIXED_GAIN = 1;
        public static final int DEC_MIN_ES_FRAME_RATE = 1;
        public static final int DEC_MIN_ES_MAX_EXP = 1;
        public static final int DEC_MIN_ES_MAX_GAIN = 1;
        public static final int DEC_MIN_ES_SPECULAR_EXCLUSION = 0;
        public static final int DEC_MIN_ES_SPECULAR_LIMIT = 1;
        public static final int DEC_MIN_ES_SPECULAR_SAT = 200;
        public static final int DEC_MIN_ES_TARGET_ACCEPT_GAP = 1;
        public static final int DEC_MIN_ES_TARGET_PERCENTILE = 1;
        public static final int DEC_MIN_ES_TARGET_VALUE = 1;

        public ExposureSettingsMinMax() {
        }
    }

    public final class SpecularExclusion {
        public static final int AGGRESSIVE = 3;
        public static final int MINIMAL = 1;
        public static final int MODERATE = 2;
        public static final int OFF = 0;
        public static final int SPECIAL = 4;

        public SpecularExclusion() {
        }
    }
}
