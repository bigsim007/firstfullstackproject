package za.co.bigsim.services;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TEST {

	public static void main(String[] args) {
		String st = "12:30pm-12:00am";
		String[] sta = st.split("-");
		System.out.println(sta[0] +"aaaaaaaaaa" +sta[1]);
		DateFormat df = new SimpleDateFormat("hh:mma");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:ma");
		LocalTime t = LocalTime.parse(sta[0], dtf);//new Time(df.parse(sta[0]).getTime());
		LocalTime t2 = LocalTime.parse(sta[1], dtf);
		//Time t2 = new Time(df.parse(sta[1]).getTime());
		
		Duration d = Duration.between(t2, t);
		System.out.println(d.toMinutes());
		//Long c = t2.getTime() - t.getTime();
		//System.out.println(c/60000);

	}

}
