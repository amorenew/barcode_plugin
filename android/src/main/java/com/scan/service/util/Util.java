package com.scan.service.util;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Util {
    public static Context context;
    static int count = 0;
    public static SoundPool sp;
    public static Map<Integer, Integer> suondMap;

   /* public static void initSoundPool(Context context2) {
        context = context2;
        sp = new SoundPool(1, 3, 1);
        suondMap = new HashMap();
        suondMap.put(1, Integer.valueOf(sp.load(context2, R.raw.msg, 1)));
    }*/

 /*   public static void play(int sound, int number) {
        AudioManager am = (AudioManager) context.getSystemService("audio");
        float audioMaxVolume = (float) am.getStreamMaxVolume(3);
        float audioCurrentVolume = (float) am.getStreamVolume(3);
        float f = audioCurrentVolume / audioMaxVolume;
        sp.play(suondMap.get(Integer.valueOf(sound)).intValue(), audioCurrentVolume, audioCurrentVolume, 1, number, 1.0f);
    }*/

    public static void logE(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void writeLog(Object obj) {
        File file = new File("/mnt/sdcard/count.txt");
        if (!file.exists()) {
            count = 0;
        }
        count++;
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("count = " + count);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
