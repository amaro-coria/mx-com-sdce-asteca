package mx.com.asteca.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private Matcher matcher;
	private Pattern pattern;
	
	private static EmailValidator instance = new EmailValidator();
	
	public static EmailValidator getInstance(){
		return instance;
	}
	
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private EmailValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	/**
	 * @param hex
	 * @return
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
}
