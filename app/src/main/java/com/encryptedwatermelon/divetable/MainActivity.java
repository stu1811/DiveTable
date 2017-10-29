package com.encryptedwatermelon.divetable;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.view.inputmethod.*;
import android.content.Context;
import android.graphics.Color;
import android.content.res.*;
import android.text.*;



public class MainActivity extends Activity
{
	private ColorStateList defaultColorsTextView;
	private ColorStateList defaultColorsEditText;
	private EditText SIMin;
	private EditText FO2;
	private NumberPicker PGPicker;
	private EditText currentO2Percent;
	private TextView ppO2;
	private TextView percentO2;
	private TextView totalO2;

	//Column 0 is depth
	//Column 1 is depth requiring safety stop
	//Column 2 starts tabl1 from padi dive tables
	private final int [][] table1 = new int[][] {
		{35, 139, 10, 19, 25, 29, 32, 36, 40, 44, 48, 52, 57, 62, 67, 73, 79, 85, 92, 100, 108, 117, 127, 139, 152, 168, 188, 205},
		{40, 104, 9, 16, 22, 25, 27, 31, 34, 37, 40, 44, 48, 51, 55, 60, 64, 69, 74, 79, 85, 91, 97, 104, 111, 120, 129, 140},
		{50, 63, 7, 13, 17, 19, 21, 24, 26, 28, 31, 33, 36, 39, 41, 44, 47, 50, 53, 57, 60, 63, 67, 71, 75, 80},
		{60, 47, 6, 11, 14, 16, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 42, 44, 47, 49, 52, 54, 55},
		{70, 33, 5, 9, 12, 13, 15, 16, 18, 19, 21, 22, 24, 26, 27, 29, 31, 33, 35, 36, 38, 40},
		{80, 25, 4, 8, 10, 11, 13, 14, 15, 17, 18, 19, 21, 22, 23, 25, 26, 28, 29, 30},
		{90, 21, 4, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25},
		{100, 3, 3, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
		{110, 3, 3, 6, 7, 8, 9, 10, 11, 12, 13, -1, 14, 15, 16},
		{120, 3, 3, 5, 6, 7, 8, 9, 10, 11, -1, 12, 13},
		{130, 3, 3, 5, 6, 7, -1, 8, 9, 10},
		{140, 0, -1, 4, 5, 6, 7, 8}
	};

	private final int table2[][] = 
	{
		//A
		{3 * 60, Integer.MAX_VALUE},
		//B
		{47, 3 * 60 + 48,Integer.MAX_VALUE},
		//C
		{21, 60 + 9, 4 * 60 + 10, Integer.MAX_VALUE},
		//D
		{8, 30, 60 + 18, 4 * 60 + 19, Integer.MAX_VALUE},
		//E
		{7, 16, 38, 60 + 27, 4 * 60 + 28, Integer.MAX_VALUE},
		//F
		{7, 15, 24, 46, 60 + 34, 4 * 60 + 35, Integer.MAX_VALUE},
		//G
		{6, 13, 22, 31, 53, 60 + 41, 4 * 60 + 42, Integer.MAX_VALUE},
		//H
		{5, 12, 20, 28, 37, 59, 60 + 47, 4 * 60 + 48, Integer.MAX_VALUE},
		//I
		{5, 11, 18, 26, 34, 43, 60 + 5, 60 + 53, 4 * 60 + 54, Integer.MAX_VALUE},
		//J
		{5, 11, 17, 24, 31, 40, 49, 60 + 11, 60 + 59, 5 * 60, Integer.MAX_VALUE},
		//K
		{4, 10, 16, 22, 29, 37, 45, 54, 60 + 16, 2 * 60 + 4, 5 * 60 + 5, Integer.MAX_VALUE},
		//L
		{4,9, 15, 21, 27, 34, 42, 50, 59, 60 + 21, 2 * 60 + 9, 5 * 60 + 10, Integer.MAX_VALUE},
		//M
		{4, 9, 14, 19, 25, 32,  39, 46, 55, 60 + 4, 60 + 25, 2 * 60 + 14, 5 * 60 + 15, Integer.MAX_VALUE},
		//N
		{3, 8, 13, 18, 24, 30, 36, 43, 51, 59, 60 + 8, 60 + 30, 2 * 60 + 18, 5 * 60 + 19, Integer.MAX_VALUE},
		//O
		{3, 8, 12, 17, 23, 28, 34, 41, 47, 55, 60 + 3, 60 + 12, 60 + 34, 2 * 60 + 23, 5 * 60 + 24, Integer.MAX_VALUE},
		//P
		{3, 7, 12, 16, 21, 27, 32, 38, 45, 51,  59, 60 + 7, 60 + 16, 60 + 38, 2 * 60 + 27, 5 * 60 + 28, Integer.MAX_VALUE},
		//Q
		{3, 7, 11, 16, 20, 25, 30, 36, 42, 48, 55, 60 + 3, 60 + 11, 60 + 20, 60 + 42, 2 * 60 + 30, 5 * 60 + 31, Integer.MAX_VALUE},
		//R
		{3, 7, 11, 15, 19, 24, 29, 34, 40, 46, 52, 59, 60 + 7, 60 + 15, 60 + 24, 60 + 46, 2 * 60 + 34, 5 * 60 + 35, Integer.MAX_VALUE},
		//S
		{3, 6, 10, 14, 18, 23, 27, 32, 38, 43, 49, 56, 60 + 3, 60 + 10, 60 + 18, 60 + 27, 60 + 49, 2 * 60 + 38, 5 * 60 + 39, Integer.MAX_VALUE},
		//T
		{2, 6, 10, 13, 17, 22, 26, 31, 36, 41, 47, 53, 59, 60 + 6, 60 + 13, 60 + 22, 60 + 31, 60 + 53, 2 * 60 + 41, 5 * 60 + 42, Integer.MAX_VALUE},
		//U
		{2, 6, 9, 13, 17, 21, 25, 29, 34, 39, 44, 50, 56, 60 + 2, 60 + 9, 60 + 17, 60 + 25, 60 + 34, 60 + 56, 2 * 60 + 44, 5 * 60 + 45, Integer.MAX_VALUE},
		//V
		{2, 5, 9, 12, 16, 20, 24, 28, 33, 37, 42, 47, 53, 59, 60 + 5, 60 + 12, 60 + 20, 60 + 28, 60 + 37, 60 + 59, 2 * 60 + 47, 5 * 60 + 48, Integer.MAX_VALUE},
		//W
		{2, 5, 8, 12, 15, 19, 24, 27, 31, 36, 40, 45, 50, 56, 60 + 2, 60 + 8, 60 + 15, 60 + 23, 60 + 31, 60 + 40, 2 * 60 + 2, 2 * 60 + 50, 5 * 60 + 51, Integer.MAX_VALUE},
		//X
		{2, 5, 8, 11, 15, 18, 22, 26, 30, 34, 39, 43, 48, 53, 59, 60 + 5,60 + 11, 60 + 18, 60 + 26, 60 + 34, 60 + 43, 2 * 60 + 5, 2 * 60 + 53, 5 * 60 + 54, Integer.MAX_VALUE},
		//Y
		{2, 5, 8, 11, 14, 18, 21, 25, 29, 33, 37, 41, 46, 51, 56, 60 + 2, 60 + 8, 60 + 14, 60 + 21, 60 + 29, 60 + 37, 60 + 46, 2 * 60 + 8, 2 * 60 + 56, 5 * 60 + 57, Integer.MAX_VALUE},
		//Z
		{2, 5, 8, 11, 14, 17, 20, 24, 28, 31, 35, 40, 44, 49, 54, 59, 60 + 5, 60 + 11, 60 + 17, 60 + 24, 60 + 31, 60 + 40, 60 + 49, 2 * 60 + 11, 2 * 60 + 59, 6 * 60, Integer.MAX_VALUE}
	};

	private final int table3[][] = 
	{
		//35 feet
		{35, 10, 19, 25, 29, 32, 36, 40, 44, 48, 52, 57, 62, 67, 73, 79, 85, 92, 100, 108, 117, 127, 139, 162, 168, 188, 205},
		//40 feet
		{40, 9, 16, 22, 25, 27, 31, 34, 37, 40, 44, 48, 51, 55, 60, 64, 69, 74, 79, 85, 91, 97, 104, 111, 120, 129, 140},
		//50 feet
		{50, 7, 13, 17, 19, 21, 24, 26, 28, 31, 33, 36, 38, 41, 44, 47, 50, 53, 57, 60, 63, 67, 71, 75, 80},
		//60 feet
		{60, 6, 11, 14, 16, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 42, 44, 47, 49, 52, 54, 55},
		//70 feet
		{70, 5, 9, 12, 13, 15, 16, 18, 19, 21, 22, 24, 26, 27, 29, 31, 33, 34, 36, 38, 40},
		//80 feet
		{80, 4, 8, 10, 11, 13, 14, 15, 17, 18, 19, 21, 22, 23, 25, 26, 28, 29, 30},
		//90 feet
		{90, 4, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25},
		//100 feet
		{100, 3, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
		//110 feet
		{110, 3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 15, 16},
		//120 feet
		{120, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12, 13},
		//130 feet
		{130, 3, 5, 6, 7, 8, 8, 9, 10}
	};
	
	//Lookup table for oxygen level based on EAD
	//Column zero is depth in feet
	//Column one is partial pressure
	private final float ppOxy[][] = {
		//20 feet
		{20, (float)0.34},
		//30 feet
		{30, (float)0.40},
		//40 feet
		{40, (float)0.46},
		//50 feet
		{50, (float)0.53},
		//60 feet
		{60, (float)0.59},
		//70 feet
		{70, (float)0.66},
		//80 feet
		{80, (float)0.72},
		//90 feet
		{90, (float)0.78},
		//100 feet
		{100, (float)0.85},
		//110 feet
		{110, (float)0.91},
		//120 feet
		{120, (float)0.97},
		//130 feet
		{130, (float)1.04},
		//140 feet
		{140, (float)1.10}
	};
	
	//Column zero is partial pressure oxygen
	//Column n is 5*n percent oxygen used of 24 hour allotment
	//Returns percent oxygen used in 24 hour period
	private final float percentOxy[][] = {
		{(float)0.6, 36, 72, 108, 144, 180, 216, 252, 288, 324, 360, 396, 432, 468, 504, 540, 576, 612, 648, 684, 720},
		{(float)0.7, 29, 57, 86, 114, 143, 171, 200, 228, 257, 285, 314, 342, 371, 399, 428, 456, 485, 513, 542, 570},
		{(float)0.8, 23, 45, 68, 90, 113, 135, 158, 180, 203, 225, 248, 270, 293, 315, 338, 360, 383, 405, 428, 450},
		{(float)0.9, 18, 36, 54, 72, 90, 108, 126, 144, 162, 180, 198, 216, 234, 252, 270, 288, 306, 324, 342, 360},
		{(float)1.0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180, 195, 210, 225, 240, 255, 270, 285, 300},
		{(float)1.1, 12, 24, 36, 48, 60, 72, 84, 96, 108, 120, 132, 144, 156, 168, 180, 192, 204, 216, 228, 240},
		{(float)1.2, 11, 21, 32, 42, 53, 63, 74, 84, 95, 105, 116, 126, 137, 147, 158, 168, 179, 189, 200, 210},
		{(float)1.3, 9, 18, 27, 36, 45, 54, 63, 72, 81, 90, 99, 108, 117, 126, 135, 144, 153, 162, 171, 180},
		{(float)1.4, 8, 15, 23, 30, 38, 45, 53, 60, 68, 75, 83, 90, 98, 105, 113, 120, 128, 135, 143, 150},
		{(float)1.5, 6, 12, 18, 24, 30, 36, 42, 48, 54, 60, 66, 72, 78, 84, 90, 96, 102, 108, 114, 120},
		{(float)1.6, 3, 5, 7, 9, 12, 14, 16, 18, 21, 23, 25, 27, 30, 32, 34, 36, 39, 41, 43, 45}
	};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		ppO2 = ((TextView) findViewById(R.id.ppO2Text));
		percentO2 = ((TextView)findViewById(R.id.percentO2Text));
		totalO2 = ((TextView) findViewById(R.id.totalO2Text));
		defaultColorsTextView =  ((TextView) findViewById(R.id.ead)).getTextColors();
		defaultColorsEditText =  ((EditText) findViewById(R.id.bt)).getTextColors();
		PGPicker = ((NumberPicker) findViewById(R.id.PGPicker));
		
		//Setup picker for pressure group characters
		PGPicker.setWrapSelectorWheel(false);
		String [] input = new String [] {"-", "A", "B", "C", "D", "E",
						 "F", "G", "H", "I", "J", "K",
						 "L", "M", "N", "O", "P", "Q",
						 "R", "S", "T", "U", "V", "W",
						 "X", "Y", "Z"};
		PGPicker.setDisplayedValues(input);
		PGPicker.setMinValue(0);
		PGPicker.setMaxValue(input.length - 1);
		
		//Setup picker for current percent oxygen used
		currentO2Percent = ((EditText) findViewById(R.id.currentO2Percent));
		currentO2Percent.setFilters(new InputFilter[]{new InputFilterMinMax(0, 100, 3)});

		SIMin = (EditText) findViewById(R.id.SIMin);
		//Set min=0, max=59, and character limit=2
		SIMin.setFilters(new InputFilter[]{new InputFilterMinMax(0, 59, 2)});

		//Filter out leading zero
		SIMin.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable e)
				{
					//Do nothing
				}
				public void onTextChanged(CharSequence s, int start,  int before, int count)
				{
					if (s.length() == 0)
					{
						return;
					}
					else if (s.toString().charAt(0) == '0' && s.toString().length() > 1)
					{
						SIMin.setText(s.toString().substring(1, s.toString().length()));
						SIMin.setSelection(s.length() - 1);
					}
				}

				public void beforeTextChanged(CharSequence s, int start,  int before, int count)
				{
					//Do nothing
				}
			});

		FO2 = (EditText) findViewById(R.id.mix);
		//Filter out leading zero
		FO2.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable e)
				{
					//Do nothing
				}
				public void onTextChanged(CharSequence s, int start,  int before, int count)
				{
					if (s.length() == 0)
					{
						return;
					}
					else if (s.toString().charAt(0) == '0' && s.toString().length() > 1)
					{
						SIMin.setText(s.toString().substring(1, s.toString().length()));
						SIMin.setSelection(s.length() - 1);
					}
				}

				public void beforeTextChanged(CharSequence s, int start,  int before, int count)
				{
					//Do nothing
				}
			});

		//Add listener to bottom time
		EditText bottomTime = (EditText) findViewById(R.id.bt);
		bottomTime.setOnEditorActionListener(new EditText.OnEditorActionListener(){
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
				{
					if (actionId == EditorInfo.IME_ACTION_DONE)
					{
						calcULATE();
					}
					return true;
				}
			});

		//Add listener to surface interval
		EditText surfaceInterval = (EditText) findViewById(R.id.SIMin);
		surfaceInterval.setOnEditorActionListener(new EditText.OnEditorActionListener(){
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
				{
					if (actionId == EditorInfo.IME_ACTION_DONE)
					{
						calcULATE();
					}
					return true;
				}
			});
			
		//Add listener to current oxy
		currentO2Percent.setOnEditorActionListener(new EditText.OnEditorActionListener(){
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
				{
					if (actionId == EditorInfo.IME_ACTION_DONE)
					{
						calcULATE();
					}
					return true;
				}
			});
			
    } //End On Create

	//Zero out PG and SI if bottom time is not available
	private void zeroOutNoTime()
	{
		((TextView) findViewById(R.id.pgText)).setText("-");
		((TextView) findViewById(R.id.ssMinText)).setText("");
		((TextView) findViewById(R.id.ssText)).setText("SS Recommended: ");
	}


	private void calcULATE()
	{
		//Assume mix is air unless specified
		float mix=(float) 0.21;
		int ead;
		EditText temp= (EditText) findViewById(R.id.depth);
		//If depth DNE do nothing and hide keyboard
		if (temp.getText().toString().trim().length() == 0)
		{
			hideKeyboard();
			return;
		}
		float depth= Float.parseFloat(temp.getText().toString());
		temp = (EditText) findViewById(R.id.mix);
		if (temp.getText().toString().trim().length() == 0)
		{
			mix = (float) 1 - mix;
			ead=Math.round(depth);
			((TextView) findViewById(R.id.ead)).setText(Integer.toString(ead));
		}
		else
		{
			mix = Float.parseFloat(temp.getText().toString());
			if (mix > 100 || mix < 21)
			{
				Toast.makeText(getApplicationContext(), "FO2 outside expected range", Toast.LENGTH_LONG).show();
				FO2.setTextColor(Color.RED);
				zeroOutNoTime();
				hideKeyboard();
				return;
			}
			if (FO2.getTextColors().getDefaultColor() == Color.RED)
			{
				FO2.setTextColor(defaultColorsEditText);
			}

			mix = (1 - (mix/ 100));
			ead=Math.round(((mix / (float) 0.79) * (depth + (float) 10)) - 10);
			((TextView) findViewById(R.id.ead)).setText(Integer.toString(ead));
		}
		//calc mod
		int mod= (int) Math.round(33 * ((1.4 / (1 - mix)) - 1));
		int maxMOD= (int) Math.round(33 * ((1.6 / (1 - mix)) - 1));
		if (mod >= 140)
		{
			mod = 140;
		}

		//Warn user if planned dive exceeds maximum or contingency depth
		((TextView)findViewById(R.id.modfsw)).setText(Integer.toString(mod));
		if (ead > maxMOD)
		{
			Toast.makeText(getApplicationContext(), "Depth exceeds contingency depth", Toast.LENGTH_LONG).show();
			((TextView) findViewById(R.id.ead)).setTextColor(Color.RED);
		}
		else if (ead > mod)
		{
			Toast.makeText(getApplicationContext(), "Depth exceeds MOD", Toast.LENGTH_LONG).show();
			((TextView) findViewById(R.id.ead)).setTextColor(Color.RED);
		}
		else
		{
			if (((TextView) findViewById(R.id.ead)).getTextColors().getDefaultColor() == Color.RED)
			{
				((TextView) findViewById(R.id.ead)).setTextColor(defaultColorsTextView);
			}
		}
		//Print NDL, PG, SS
		int ndl=getNDL(ead);
		((TextView) findViewById(R.id.ndlText)).setText(Integer.toString(ndl));

		//If bottom time is blank clear SS and PG
		temp = (EditText) findViewById(R.id.bt);
		if (temp.getText().toString().trim().length() > 0)
		{
			int bt=Integer.parseInt(((EditText)findViewById(R.id.bt)).getText().toString());
			int startingPG = PGPicker.getValue();
			if(!(startingPG==0))
			{
				for(int i=0; i< table3.length; i++)
				{
					if(ead <= table3[i][0])
					{
						if(table3[i].length<startingPG+1)
						{
							bt=999;
						}
						else
						{
							bt+=table3[i][startingPG];
						}
						break;
					}
				}
			}
			PG pg = getPressureGroup(ead, bt);

			((TextView) findViewById(R.id.pgText)).setText(Character.toString(pg.PG));
			if (pg.ssReq)
			{
				((TextView) findViewById(R.id.ssText)).setText("SS Required: ");
				((TextView) findViewById(R.id.ssMinText)).setText("3 min");
			}
			else
			{
				((TextView) findViewById(R.id.ssText)).setText("SS Recommended: ");
				((TextView) findViewById(R.id.ssMinText)).setText("3 min");
			}

			if (bt > ndl)
			{
				Toast.makeText(getApplicationContext(), "Bottom time exceeds NDL", Toast.LENGTH_LONG).show();
				((EditText) findViewById(R.id.bt)).setTextColor(Color.RED);
				((TextView) findViewById(R.id.ssMinText)).setText("-");
				((TextView) findViewById(R.id.pgPostSI)).setText("-");
				percentO2.setText("-");
				totalO2.setText("-");
			}
			else
			{
				if (((EditText) findViewById(R.id.bt)).getTextColors().getDefaultColor() == Color.RED)
				{
					((EditText) findViewById(R.id.bt)).setTextColor(defaultColorsEditText);
				}
				int oxy = calcOxyUsed(ead, bt);
				//((TextView) findViewById(R.id.percentO2Text)).setText("foo");
				percentO2.setText(Integer.toString(oxy));
				int currentO2 = 0;
				if(!currentO2Percent.getText().toString().equals(""))
				{
					currentO2 = Integer.parseInt(currentO2Percent.getText().toString());
				}
				totalO2.setText(Integer.toString(oxy+currentO2));
			}

			//If PG is not null and surface interval is enteted calc new PG
			temp = ((EditText) findViewById(R.id.SIHour));
			int SI=0;
			if (temp.getText().toString().length() > 0)
			{
				SI = 60 * Integer.parseInt(temp.getText().toString().trim());
			}
			temp = ((EditText) findViewById(R.id.SIMin));
			if (temp.getText().toString().trim().length() > 0)
			{
				SI += Integer.parseInt(temp.getText().toString().trim());
			}

			if (pg.PG != '-')
			{
				char newPG = surfaceIntervalPG(pg.PG, SI);
				((TextView) findViewById(R.id.pgPostSI)).setText(Character.toString(newPG));
			}
		}
		else
		{
			zeroOutNoTime();
		}

		hideKeyboard();
		return;
	} //End Calculate Function

	private void hideKeyboard()
	{
		View view = getCurrentFocus();
		if (view != null)
		{
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	class PG
	{
		char PG;
		boolean ssReq;
	}

	//Returns no decompression limit
	private int getNDL(int depth)
	{
		int retVal=0;
		for (int i=0; i < table1.length; i++)
		{
			if (depth <= table1[i][0])
			{
				retVal = table1[i][table1[i].length - 1];
				break;
			}
		}
		return retVal;
	}

	//Returns pressure group based on depth and bottom time
	private PG getPressureGroup(int depth, int bottomTime)
	{
		PG pg= new PG();
		pg.PG = '-';
		pg.ssReq = false;


		int row=-1;
		for (int i=0; i < table1.length; i++)
		{
			if (depth <= table1[i][0])
			{
				row = i;
				break;
			}
		}
		if (row == -1)
		{
			return pg;
		}
		for (int i=2; i < table1[row].length; i++)
		{
			if (bottomTime <= table1[row][i])
			{
				pg.PG = (char)(63 + i);
				break;
			}
		}
		if (bottomTime > table1[row][1])
		{
			pg.ssReq = true;
		}

		return pg;


	}

	//Returns new pressure group after surface interval
	private char surfaceIntervalPG(char pg, int time)
	{
		if (time == 0)
		{
			return '-';
		}
		int [] temp = new int[0];
		int pgToInt=pg - 65;

		temp = table2[pgToInt];

		for (int i=0; i < temp.length; i ++)
		{
			if (time <= temp[i])
			{
				pg = (char)(pg - i);
				break;
			}
		}
		if (pg == '@')
		{
			pg = '-';
		}
		return pg;
	}
	
	//Calculates percent oxygen used for 24 hour period
	//Depth is assumed to be EAD
	//bottomTime is in minutes
	private int calcOxyUsed(float depth, int bottomTime)
	{
		float pp=0;
		for(int i=0; i<ppOxy.length;i++)
		{
			if(depth<=ppOxy[i][0])
			{
				pp=ppOxy[i][1];
				break;
			}
		}
		//Depth greater than 140 ft
		if(pp==0)
		{
			return -1;
		}
		for(int i=0; i< percentOxy.length; i++)
		{
			if(pp<=percentOxy[i][0])
			{
				for(int j=1; j<percentOxy[i].length; j++)
				{
					if(bottomTime<=percentOxy[i][j])
					{
						return j*5;
					}
				}
			}
		}
		//Should never get here
		return -1;
	}
}
