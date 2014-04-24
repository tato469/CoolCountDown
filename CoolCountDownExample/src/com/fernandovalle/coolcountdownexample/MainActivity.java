package com.fernandovalle.coolcountdownexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	private CounterFragment counterFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Counter
		//new Counter that counts 3000 ms with a tick each 1000 ms
	    counterFragment = (CounterFragment) getSupportFragmentManager().findFragmentById(R.id.counterFragment);
		counterFragment.setTime(20000);
		counterFragment.start();
	}



}
