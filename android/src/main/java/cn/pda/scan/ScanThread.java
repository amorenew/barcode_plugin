package cn.pda.scan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.pda.serialport.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class ScanThread extends Thread {
    public static final int SCAN = 1001;
    public static int SWITCH_INPUT = 1002;
    private final byte END = 3;
    private final byte START = 2;
    private int baudrate = 9600;
    private int flags = 0;
    private Handler handler;
    private InputStream is;
    /* access modifiers changed from: private */
    public SerialPort mSerialPort;
    Timer mtimer = null;
    private OutputStream os;
    private int port = 3;

    public ScanThread(Handler handler2) throws SecurityException, IOException {
        this.handler = handler2;
        this.mSerialPort = new SerialPort(this.port, this.baudrate, this.flags);
        this.is = this.mSerialPort.getInputStream();
        this.os = this.mSerialPort.getOutputStream();
        this.mSerialPort.scaner_poweron();
        this.mSerialPort.scaner_trigoff();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.is.read(new byte[128]);
    }

    public void run() {
        try {
            byte[] buffer = new byte[4096];
            while (!isInterrupted()) {
                if (this.is.available() > 0) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int size = this.is.read(buffer);
                    if (size > 0) {
                        sendMessege(buffer, size, SCAN);
                        if (this.mtimer != null) {
                            this.mtimer.cancel();
                        }
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        super.run();
    }

    /* access modifiers changed from: private */
    public void sendMessege(byte[] data, int dataLen, int mode) {
        String dataStr = "";
        if (data != null && dataLen > 0) {
            dataStr = new String(data, 0, dataLen).replaceAll("\r\n", "");
        }
        Bundle bundle = new Bundle();
        bundle.putString("data", dataStr);
        Message msg = new Message();
        msg.what = mode;
        msg.setData(bundle);
        this.handler.sendMessage(msg);
    }

    public void scan() {
        this.mSerialPort.scaner_poweron();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        if (this.mtimer != null) {
            this.mtimer.cancel();
        }
        this.mtimer = new Timer();
        this.mtimer.schedule(new TimerTask() {
            public void run() {
                ScanThread.this.mSerialPort.scaner_trigoff();
                ScanThread.this.sendMessege((byte[]) null, 0, ScanThread.SWITCH_INPUT);
            }
        }, 5000);
        if (this.mSerialPort.scaner_trig_stat()) {
            this.mSerialPort.scaner_trigoff();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        this.mSerialPort.scaner_trigon();
    }

    public void close() {
        if (this.mSerialPort != null) {
            this.mSerialPort.scaner_poweroff();
            try {
                if (this.is != null) {
                    this.is.close();
                }
                if (this.os != null) {
                    this.os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mSerialPort.close(this.port);
        }
    }
}
