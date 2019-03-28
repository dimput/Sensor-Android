package com.example.sensorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private TextView sensorProx,sensorSuhu,sensorAcc,sensorHumidity,sensorLamp,sensorMagnet;
    private Sensor sensorProximity,sensorTemperature,sensorAccelerometer,sensorKelembaban,sensorLampu,sensorMagneto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorProx = (TextView) findViewById(R.id.sensol_prox);
        sensorSuhu = (TextView) findViewById(R.id.sensor_suhu);
        sensorLamp = (TextView) findViewById(R.id.sensor_lamp);
        sensorMagnet = (TextView) findViewById(R.id.sensor_magnet);
        sensorAcc = (TextView) findViewById(R.id.sensor_acc);
        sensorHumidity = (TextView) findViewById(R.id.sensor_humidity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sensorProximity==null){
            sensorProx.setText("No Sensor !");
        }
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(sensorTemperature==null){
            sensorSuhu.setText("No Sensor !");
        }
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensorAccelerometer==null){
            sensorAcc.setText("No Sensor !");
        }
        sensorKelembaban = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(sensorKelembaban==null){
            sensorHumidity.setText("No Sensor !");
        }
        sensorLampu = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sensorLampu==null){
            sensorLamp.setText("No Sensor !");
        }
        sensorMagneto = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(sensorMagneto==null){
            sensorMagnet.setText("No Sensor !");
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        if(sensorProximity!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorProximity,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(sensorTemperature!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorTemperature,SensorManager.SENSOR_DELAY_NORMAL);
        }if(sensorAccelerometer!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }if(sensorKelembaban!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorKelembaban,SensorManager.SENSOR_DELAY_NORMAL);
        }if(sensorLampu!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorLampu,SensorManager.SENSOR_DELAY_NORMAL);
        }if(sensorMagneto!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorMagneto,SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @Override
    protected void onStop(){
        super.onStop();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        int sensorType = sensorEvent.sensor.getType();
        float value = sensorEvent.values[0];
        if(sensorType==Sensor.TYPE_PROXIMITY){
            sensorProx.setText("Prox : "+value);
        }
        if(sensorType==Sensor.TYPE_ACCELEROMETER){
            sensorAcc.setText("ACC : "+value);
        }
        if(sensorType==Sensor.TYPE_AMBIENT_TEMPERATURE){
            sensorSuhu.setText("Suhu : "+value);
        }
        if(sensorType==Sensor.TYPE_RELATIVE_HUMIDITY){
            sensorHumidity.setText("Kelembapan : "+value);
        }
        if(sensorType==Sensor.TYPE_LIGHT){
            sensorLamp.setText("Light : "+value);
        }
        if(sensorType==Sensor.TYPE_MAGNETIC_FIELD){
            sensorMagnet.setText("Magnetic : "+value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
