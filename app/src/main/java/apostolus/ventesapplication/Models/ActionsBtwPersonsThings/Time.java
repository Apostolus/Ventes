package apostolus.ventesapplication.Models.ActionsBtwPersonsThings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time{
	
	protected final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	protected final SimpleDateFormat HEURE_DATE = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private  Date date;
	
	
	public Time() {
		this.date = new Date();
	}
	
	public Time(Time time) {
		this.date = time.date;
	}
	
	public Time(Date date) {
		this.date = date;
	}
	
	@Override
	protected Time clone() throws CloneNotSupportedException {
		return new Time(this);
	}
	
	@SuppressWarnings("deprecation")
	public Time(int years, int months, int days) {
		Date date = new Date();
		date.setYear(years);
		date.setMonth(months);
		date.setDate(days);
		
		this.date = date;
	}

	public String getStringDateFormat() {
		return FORMAT_DATE.format(this.date);
	}

	public String getStringDateEtHeureFormat() {
		return HEURE_DATE.format(this.date);
	}

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date extraiteDateFromString(String stringDate) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date date = null;
    	
    	try {
    		date = dateFormat.parse(stringDate);
    	}catch (ParseException exceptionDate) {
    		exceptionDate.printStackTrace();
    	}
    	
    	return date;
    }
    
    public void actualiseDateToCurrent() {
    	this.date = new Date();
    }
    
    @SuppressWarnings("deprecation")
	public Date addToCurrent(Date date) {
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(this.date);
    	calendar.add(Calendar.SECOND, date.getSeconds());
    	calendar.add(Calendar.MINUTE, date.getMinutes());
    	calendar.add(Calendar.HOUR, date.getHours());
    	calendar.add(Calendar.DAY_OF_MONTH, date.getDay());
    	calendar.add(Calendar.MONTH, date.getMonth());
    	calendar.add(Calendar.YEAR, date.getYear());
    	
    	return calendar.getTime();
    	
    }
}
