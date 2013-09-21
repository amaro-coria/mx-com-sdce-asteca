package mx.com.asteca.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {

	private static FechaUtil instance = new FechaUtil();

	private SimpleDateFormat sdfMM_dd_yy;

	private FechaUtil() {
		sdfMM_dd_yy = new SimpleDateFormat("MM/dd/yy");
	}

	public static FechaUtil getInstance() {
		return instance;
	}

	public String parseDateMM_dd_yy(Date date) {
		if(date == null){
			return null;
		}
		String dateStr = sdfMM_dd_yy.format(date);
		return dateStr;
	}

	public Date parseDateMM_dd_yy(String date) {
		if(date == null || date.isEmpty()){
			return null;
		}
		Date dateParsed = null;
		try {
			dateParsed = sdfMM_dd_yy.parse(date);
		} catch (ParseException e) {
			// TODO bajar a bitacora/log
		}
		return dateParsed;
	}
}
