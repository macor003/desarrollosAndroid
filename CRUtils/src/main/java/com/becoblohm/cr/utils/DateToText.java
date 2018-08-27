package com.becoblohm.cr.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class DateToText {
	private static final String[] monthNames = {
		"Enero",
		"Febrero",
		"Marzo",
		"Abril",
		"Mayo",
		"Junio",
		"Julio",
		"Agosto",
		"Septiembre",
		"Octubre",
		"Noviembre",
		"Diciembre"
	};
	public static String convert(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR),
			day = cal.get(Calendar.DAY_OF_MONTH);
		
		return day+" de "+DateToText.monthToText(cal)+" de "+year;
	}
	
	public static String monthToText(Calendar cal){
		DateFormatSymbols format = new DateFormatSymbols();
		format.setMonths(monthNames);
		return format.getMonths()[cal.get(Calendar.MONTH)];
	}
	
	public static void main(String[] args){
		Calendar cal = Calendar.getInstance();
		System.out.println(DateToText.convert(new Date()));
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH,23);
		//23 de marzo del 2014
		System.out.println(DateToText.convert(cal.getTime()));
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH,31);
		//31 Diciembre 2016
		System.out.println(DateToText.convert(cal.getTime()));
	}
}
