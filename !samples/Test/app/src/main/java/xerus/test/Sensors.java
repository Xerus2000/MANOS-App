package xerus.test;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.*;

/**
 * Created by Janek on 03.07.2017.
 */

public class Sensors implements SensorEventListener {

    public static Sensors singleton;
    public static SensorManager manager;
    public static HashMap<Integer, Collection<SensorListener>> listeners;

    public static void init(Activity a) {
        manager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
        listeners = new HashMap();
        singleton = new Sensors();
    }

    public static boolean register(int id, SensorListener listener, int delay) {
        Collection entry = listeners.get(id);
        if(entry != null) {
            entry.add(listener);
            return true;
        }

        if(manager.registerListener(singleton, manager.getDefaultSensor(id), delay)) {
            entry = new HashSet<SensorListener>();
            entry.add(listener);
            listeners.put(id, entry);
            return true;
        }
        return false;
    }

    public static boolean register(int id, SensorListener listener) {
        return register(id, listener, 1000);
    }

    public static void unregister(int id) {
        listeners.remove(id);
    }

    public static void unregisterAll() {
        listeners.clear();
    }

    public interface SensorListener {
        void receive(SensorEvent e);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Collection<SensorListener> l = listeners.get(event.sensor.getType());
        for (SensorListener s : l)
            s.receive(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
