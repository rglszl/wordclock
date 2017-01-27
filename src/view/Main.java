package view;

import java.util.GregorianCalendar;

import model.Model;

public class Main {

	public static void main(String[] args) {
		String max = new String();
		for(int h = 0; h<24; h++){
			for(int m = 0; m<60; m+=5){
				boolean[] data = Model.convertToBoolean(h, m);
				String current =  Model.getStringData(data);
				System.out.println(String.format("%02d", h) + ":" + String.format("%02d", m) + "\t" + current);
				if(current.length()> max.length()) max = new String(current);
			}
		}
		System.out.println("\n\n");
		
		System.out.println("A leghosszabb: " + max + "\nHossza: " + max.length());
		
		System.out.println("\n\n");
		
	
		int hour = GregorianCalendar.getInstance().get(GregorianCalendar.HOUR);
		int minute = GregorianCalendar.getInstance().get(GregorianCalendar.MINUTE);
		
		System.out.println(String.format("%02d", hour) + ":" + String.format("%02d", minute) + "\t" + Model.getStringData(Model.convertToBoolean(hour, minute)));
	
		
		
	}

}
