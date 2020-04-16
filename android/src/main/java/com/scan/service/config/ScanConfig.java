package com.scan.service.config;

import android.content.Context;
import android.content.SharedPreferences;

public class ScanConfig {
    private Context context;

    public ScanConfig(Context context2) {
        this.context = context2;
    }

    public boolean isOpen() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("open", false);
    }

    public void setOpen(boolean open) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("open", open);
        editor.commit();
    }

    public String getPrefix() {
        return this.context.getSharedPreferences("scanConfig", 0).getString("prefix", "");
    }

    public void setPrefix(String prefix) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putString("prefix", prefix);
        editor.commit();
    }

    public String getSurfix() {
        return this.context.getSharedPreferences("scanConfig", 0).getString("surfix", "");
    }

    public void setSurfix(String surfix) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putString("surfix", surfix);
        editor.commit();
    }

    public boolean isVoice() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("open", true);
    }

    public void setVoice(boolean voice) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("voice", voice);
        editor.commit();
    }

    public boolean isF1() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("f1", true);
    }

    public void setF1(boolean f1) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("f1", f1);
        editor.commit();
    }

    public boolean isF2() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("f2", true);
    }

    public void setF2(boolean f2) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("f2", f2);
        editor.commit();
    }

    public boolean isF3() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("f3", true);
    }

    public void setF3(boolean f3) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("f3", f3);
        editor.commit();
    }

    public boolean isF4() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("f4", true);
    }

    public void setF4(boolean f4) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("f4", f4);
        editor.commit();
    }

    public boolean is1D() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("scan1d", false);
    }

    public void set1D(boolean is1D) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("scan1d", is1D);
        editor.commit();
    }

    public boolean isEnter() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("enter", false);
    }

    public void setEnter(boolean isEnter) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("enter", isEnter);
        editor.commit();
    }

    public boolean isTab() {
        return this.context.getSharedPreferences("scanConfig", 0).getBoolean("tab", false);
    }

    public void setTab(boolean isTab) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences("scanConfig", 0).edit();
        editor.putBoolean("tab", isTab);
        editor.commit();
    }
}
