CoolCountDown
=============

An example of Android Fragment that show a countDown. That is easy to configure and have special effects.

Usage:

add the class CounterFragment and the layout counter_layout.xml to your project and enjoy:

for use:

    CounterFragment counterFragment = (CounterFragment) getSupportFragmentManager().findFragmentById(R.id.counterFragment);
		counterFragment.setTime(20000);
		counterFragment.start();
		

When a half of time is over the countDown will change the color to orange.
When missing a quarter of the time to finish the seconds will "blink" and set to red.
