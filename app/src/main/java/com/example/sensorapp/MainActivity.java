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
    private TextView textView;
    private TextView sensorProx;
    private Sensor sensorProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.sensol_list);
        sensorProx = (TextView) findViewById(R.id.sensol_prox);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorText = new StringBuilder();
        for(Sensor sensor:sensorList){
            sensorText.append(sensor.getName())
                    .append(System.getProperty("Line.Separator"));
        }
        textView.setText(sensorText);

        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sensorProximity==null){
            sensorProx.setText("No Sensor !");
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(sensorProximity!=null){
            sensorManager.registerListener((SensorEventListener) this,sensorProximity,SensorManager.SENSOR_DELAY_NORMAL);
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
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
