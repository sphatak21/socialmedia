package profiles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UseDate{
	private long millis;
	public UseDate(String dateString) throws ParseException {
		String myDate = dateString;
		SimpleDateFormat sdf; 
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		
		Date date = sdf.parse(myDate);
		millis = date.getTime();
	}
	public UseDate(long time) {
		millis = time; 
	}
	public UseDate() {
		Date now = Calendar.getInstance().getTime(); 
		millis = now.getTime(); 
	}
	public long getMillis() {
		return this.millis; 
	}
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);	
		return sdf.format(calendar.getTime());
	}
}