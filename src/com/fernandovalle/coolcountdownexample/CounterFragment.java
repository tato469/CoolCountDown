package com.fernandovalle.coolcountdownexample;

import java.math.BigDecimal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CounterFragment extends Fragment {

	 protected long time = 0;
	 protected long totalTime = 0;
	 protected boolean isRunning = false;
	 CountDownTimer myCountDown;
	 long countDownInterval = 100;
	 
	 TextView minutes;
	 TextView seconds;
	 TextView milliseconds;
	 
	  
	 int colorMedium = Color.parseColor("#ee6e1a"); //orange
	 int colorDanger = Color.parseColor("#c70000"); //red
	 
	 boolean dangerEffect;
	 private boolean isDangerAnimationActivated;
	 ScaleAnimation animation;
	 
	 public CounterFragment()
	 {
		 
	 }
	 
	 public void dangerAnimation()
	 {
		 animation = new ScaleAnimation(1, 
 						2, 
 						1, 
 						2, 
 						Animation.RELATIVE_TO_SELF, 
 						(float)0.5, Animation.RELATIVE_TO_SELF, 
 						(float)0.5);
		 animation.setDuration(1000);

		 animation.setRepeatMode(Animation.RESTART);

		 animation.setRepeatCount(Animation.INFINITE);

		 seconds.startAnimation(animation);
		 
	 }
	 
	 public void setTime(int time)
	 {
		 this.time = time;
		 this.totalTime = time;
		 
		 if(minutes!=null)
		 {
			 minutes.setText(String.format("%02d", (int)time/3600));
		 }
		 if(seconds!=null)
		 {
			 seconds.setText(String.format("%02d", ((int)((new BigDecimal((float)time/3600)).floatValue()*60))%60));
		 }
		 if(milliseconds!=null)
		 {
			 milliseconds.setText(String.format("%02d", time%60));
		 }
	 }
	 
	 public void pause()
	 {
		 isRunning = false;
	 }
	 
	 
	 
	 public void start()
	 {
		isRunning = true;
		myCountDown = new CountDownTimer(time, countDownInterval) {
			    

				public void onTick(long millisUntilFinished) {
			    	time = millisUntilFinished;
			    	//Log.d("COUNTER", ""+time);
			    	if(isRunning == false)
			    		this.cancel();
			    	else
			    	{
			    		 minutes.setText(String.format("%02d", (int)time/1000/60));
						 seconds.setText(String.format("%02d", (int)(time/1000)%60));
						 milliseconds.setText(String.format("%02d", time%60));
						 
						 if(time < totalTime/2 && (time >= totalTime/4))
						 {
							 minutes.setTextColor(colorMedium);
							 seconds.setTextColor(colorMedium);
							 milliseconds.setTextColor(colorMedium);
						 }
						 if(!isDangerAnimationActivated && (time < totalTime/4))
						 {
							 dangerAnimation();
							 seconds.setTextColor(colorDanger);
							 isDangerAnimationActivated = true;
						 }
						 
			    	}
			    }

			    public void onFinish() {
			    	 minutes.setText("00");
					 seconds.setText("00");
					 milliseconds.setText("00");
					 seconds.setTextColor(colorDanger);
					 milliseconds.setTextColor(colorDanger);
					 minutes.setTextColor(colorDanger);
					 
					 animation.cancel();
			   }
			};
			myCountDown.start();
		 isRunning = true;
	 }
	 
	 
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	  }
	  
	  
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        LinearLayout view = (LinearLayout) inflater.inflate(
	                R.layout.counter_layout, container, false);
	        
	        minutes = (TextView) view.findViewById(R.id.minutes);
	        seconds = (TextView) view.findViewById(R.id.seconds);
	        milliseconds = (TextView) view.findViewById(R.id.milliseconds);
	        
	        view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(isRunning)
						CounterFragment.this.pause();
					else
						CounterFragment.this.start();
				}
	        	
	        });
	        
	        return view;
	    }
	  
}
