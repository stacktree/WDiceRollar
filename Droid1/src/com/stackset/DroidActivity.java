package com.stackset;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class DroidActivity extends Activity {
	private boolean FIRST_DICE = true;
	private int diceSum = 0;
	private int diceCounter = 0;
	private final int totalDiceAllowed = 9;
	private int[] diceArr = new int[totalDiceAllowed];
	private boolean isSetMaxRoll = true;

	// private final int histAllowed = 7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_droid);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.droid, menu);
		return true;
	}

	public void clickD4(View view) {
		oneDie(4);
	}

	public void clickD6(View view) {
		oneDie(6);
	}

	public void clickD8(View view) {
		oneDie(8);
	}

	public void clickD10(View view) {
		oneDie(10);
	}

	public void clickD12(View view) {
		oneDie(12);
	}

	public void clickD20(View view) {
		oneDie(20);
	}

	private void oneDie(int x) {
		TextView tv = (TextView) findViewById(R.id.editText1);
		if (FIRST_DICE) {
			clickClear(tv);
		}
		if ((diceCounter < totalDiceAllowed)) {
			diceArr[diceCounter] = x;
			diceCounter++;

			if (FIRST_DICE) {
				tv.setText("D" + x);
				FIRST_DICE = false;
			} else {
				tv.setText(tv.getText() + "+D" + x);
			}
		}
	}

	public void clickRoll(View view) {
		TextView tv = (TextView) findViewById(R.id.text_total);

		for (int i = 0; i < diceCounter; i++) {
			int num = genRand(diceArr[i]);
			diceSum += num;
		}
		tv.setText("Total: " + diceSum);
		FIRST_DICE = true;
		diceSum = 0;
	}

	private int genRand(int dice) {
		int randNum = 1 + (int) (Math.random() * dice);

		if (isSetMaxRoll && randNum == dice) {
			randNum += genRand(dice);
		}
		return randNum;

	}

	public void clickClear(View view) {
		TextView tv = (TextView) findViewById(R.id.text_total);
		tv.setText("Total: 0");
		TextView tv2 = (TextView) findViewById(R.id.editText1);
		tv2.setText("");
		FIRST_DICE = true;
		diceCounter = 0;
		for (int i = 0; i < diceArr.length; i++) {
			diceArr[i] = 0;
		}
		diceSum = 0;
	}

	public void clickCheck(View view) {
		CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
		if (cb.isChecked()) {
			isSetMaxRoll = true;
		} else {
			isSetMaxRoll = false;
		}
	}
}
