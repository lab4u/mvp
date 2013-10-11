package com.lab4u.sensors.persistence;

import android.os.Environment;
import android.util.Log;

import com.lab4u.LAB4UTAG;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ajperalt on 15/09/13.
 */
public class FilePersistSensorInfo implements ILab4uSensorPersistence {


    private FileOutputStream fos = null;
    private PrintStream ps = null;
    private static final String charSeparator = ";";
    private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
    private Counter counter;
    private String name = "Base";


    public FilePersistSensorInfo(String name) {
        this.name = name;
        this.init();
    }

    public FilePersistSensorInfo() {
        this.init();
    }

    public void init() {
        counter = new Counter();
        this.initFile();
    }

    public void initFile() {
        try {
            Log.v(LAB4UTAG.T, "START initFile[" + name + "]");
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()) {
                Log.v(LAB4UTAG.T, "can write continue");
                // build lab4usensor file
                File dir = new File(root.getAbsolutePath() + "/lab4u");
                dir.mkdirs();
                // build lab4u sensor by sensor
                //File dirSensor = new File(dir, name);
                //dir.mkdirs();
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
                String fileName = name + sdf.format(new Date()) + ".csv";
                File file = new File(dir, fileName);
                Log.v(LAB4UTAG.T, "FileFullPath["+file.getCanonicalPath()+"]");
                fos = new FileOutputStream(file);
                ps = new PrintStream(fos);
            }else{
                Log.v(LAB4UTAG.T, "can't write file");
            }
            Log.v(LAB4UTAG.T, "END initFile[" + name + "]");
        } catch (Exception e) {
            Log.e(FilePersistSensorInfo.class.getName(), e.getMessage());
        }
    }

    public void print(float values[]) {
        if (ps != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(formatTime.format(new Date())).append(charSeparator);
            sb.append(counter.getCounter()).append(charSeparator);
            for (float value : values) {
                sb.append(value).append(charSeparator);
            }
            sb.append("\n");
            ps.print(sb.toString());
            ps.flush();
        }

    }

    public void close() {
        try {
            Log.v(LAB4UTAG.T, "START closeFile[" + name + "]");
            if (ps != null)
                ps.close();
            if (fos != null)
                fos.close();
            Log.v(LAB4UTAG.T, "END closeFile[" + name + "]");
        } catch (IOException e) {
            Log.e(FilePersistSensorInfo.class.getName(), e.getMessage());
        }
    }


    class Counter {
        private long counter = System.currentTimeMillis();

        public long getCounter() {
            return ( System.currentTimeMillis() - counter ) / 1000;
        }
    }
}
