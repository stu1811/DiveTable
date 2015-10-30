package com.encryptedwatermelon.divetable;

import android.text.*;

public class InputFilterMinMax implements InputFilter
{
	private int min, max, length;
	
	public InputFilterMinMax(int min, int max, int length)
	{
		this.min=min;
		this.max=max;
		this.length=length;
	}
	
	public InputFilterMinMax(int min, int max)
	{
		this.min=min;
		this.max=max;
	}
	
	public InputFilterMinMax(String min, String max)
	{
		this.min=Integer.parseInt(min);
		this.max=Integer.parseInt(max);
	}
	
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
	{
		try
		{
			int input= Integer.parseInt(dest.toString() + source.toString());
			if((dest.toString()+source.toString()).length()>length)
			{
				return "";
			}
			
			if(isInRange(min, max, input))
			{
				return null;
			}
		} catch (NumberFormatException nfe) {}
		return "";
	}
	private boolean isInRange(int a, int b, int c)
	{
		return b > a ? c >= a && c <= b : c >= b && c <= a;
	}
}
