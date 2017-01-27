package model;

public class Model {
	
	public static boolean[] convertToBoolean(int hour, int minute){
		boolean[] data = new boolean[22];
		if(minute>57) hour = hour+1;	//after xx:57 the value is xx+1 o'clock
		if(hour>12) hour = hour - 12;	//in case of 24 hour format
		if(hour == 12) hour=0;			//0 and 12 is the same
		
		/* Values in the array
		 * 0 - öt
		 * 1 - tíz
		 * 2 - perc
		 * 3 - cel
		 * 4 - múlva
		 * 5 - múlt
		 * 6 - három
		 * 7 - negyed
		 * 8 - fél
		 * 9 - egy
		 * 10 - kettõ
		 * 11 - három
		 * 12 - négy
		 * 13 - öt
		 * 14 - hat
		 * 15 - hét
		 * 16 - nyolc
		 * 17 - kilenc
		 * 18 - tíz
		 * 19 - tizenegy
		 * 20 - tizenkettõ
		 * 21 - óra
		 */	
		
		for(int i = 0; i<22; i++){
			data[i] = false;
		}		
		
		int m = roundToFive(minute);
		
		data[0] = (m == 5 || m == 25 || m == 35 || m == 55);
		data[1] = (m == 10 || m == 20 || m == 40 || m == 50);
		data[2] = (m != 0 && m != 15 && m != 30 && m != 45);
		data[3] = (m == 5 || m==10 || m == 35 || m == 40);
		data[4] = (m==20 || m == 25 || m == 50 || m == 55);
		data[5] = data[3];
		data[6] = (m == 45);
		data[7] = (m == 15 || m == 45);
		data[8] = (m >= 20 && m <= 40);
		
		for(int i = 0; i<11; i++){
			data[9+i] = (( hour == i && m >= 15 ) || ( hour == i+1 && m < 15 ));
		}
		
		data[20] = (( hour == 11 && m >= 15 ) || ( hour == 0 && m < 15 ));
		data[21] = (m == 0);
		
		return data;
	}	
	
	public static String getStringData(boolean[] data){
		String result = new String();
		
		String[] words = {"öt", "tíz", "perc", "cel", "múlva", "múlt", "három", "negyed", "fél", 
				"egy", "kettõ", "három", "négy", "öt", "hat", "hét", "nyolc", 
				"kilenc", "tíz", "tizenegy", "tizenkettõ", "óra"};
		
		for(int i=0; i<data.length; i++){
			if(data[i]==true) result = result + words[i] + " ";
			if((i==2 && data[3] == true) || (i==6 && data[7] == true)) result = result.trim();
		}
		
		result = result.trim();	//trim last space
		
		return result;	
	}
	
	/*
	 * 58-59-00-01-02 -> 0
	 * 03-04-05-06-07 -> 5
	 * 08-09-10-11-12 -> 10
	 * ...
	 */
	public static int roundToFive(int number){
		number = (number+2)%60;
		number = (int)number/5;
		return number*5;
	}	
}
