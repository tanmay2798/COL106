package com.example.tanmaygoyal.gyroscope;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private SensorEventListener gyroEvent;

    float check=0;
    float check1=0;
    float check2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


       // if (mSensor == null) {
         //   Toast.makeText(this, "no gyro", Toast.LENGTH_SHORT).show();
          //  finish();
        //}

        gyroEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if(check-event.values[0]>0.05) {
                    TextView x = (TextView) findViewById(R.id.textView);
                    x.setText(Math.round(event.values[0] * 10000.0) / 10000.0 + "");
                }
                if(check1-event.values[1]>0.05 ) {
                    TextView y = (TextView) findViewById(R.id.textView7);
                    y.setText(Math.round(event.values[1] * 10000.0) / 10000.0 + "");
                }
                if(check2-event.values[2]>0.05) {
                    TextView z = (TextView) findViewById(R.id.textView3);
                    z.setText(Math.round(event.values[2]*10000.0)/10000.0 + "");
                }
                check=event.values[0];
                check1=event.values[1];
                check2=event.values[2];
                // SystemClock.sleep(1000);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    public void gyroscopes(View v){


    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(gyroEvent,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(gyroEvent);
    }

    public void compass(View v){
        Intent in = new Intent(this,compass1.class);
        startActivity(in);
    }

    public void gps(View v){
        Intent in = new Intent(this,gps1.class);
        startActivity(in);
    }
}
