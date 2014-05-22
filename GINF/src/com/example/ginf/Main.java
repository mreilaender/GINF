package com.example.ginf;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.hardware.*;

public class Main extends Activity implements OnClickListener, SensorEventListener
{
	private List<Sensor> s;
	private SensorManager sm;
	private Sensor s1, s2;
	private Button start;
	private int i2 = 0;
	private boolean a = true;
	private Iterator i;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		s = sm.getSensorList(Sensor.TYPE_ALL);

//		tmp.getName();
		start = (Button) findViewById(R.id.button1);
		start.setBackgroundColor(Color.GREEN);
		start.setOnClickListener(this);
		
		s1 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, s1, SensorManager.SENSOR_DELAY_NORMAL);
		
		s2 = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		sm.registerListener(this, s2, SensorManager.SENSOR_DELAY_FASTEST);
		i = s.iterator();
	}
	@Override
	public void onClick(View v)
	{
		start.setText("Zähler: "+i2+"\n");
//		Sensor tmp = (Sensor)i.next();
//		System.out.println("Sensor: "+tmp.getName());
		++i2;
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		start.setText(""+accuracy);
	}
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float[] temp = event.values;
//		System.out.println(""+event.sensor.getResolution());
//		if(event.sensor.equals(s2))
//			start.setText("Proximity Sensor Values has changed!\nValue[0]: "+temp[0]+"\nValue[1]"+temp[1]+"\nValue[2]"+temp[2]);
		if(event.sensor.equals(s2))
			if(((int)temp[0])==0)this.setVisible(false);
			else this.setVisible(true);
	}
}