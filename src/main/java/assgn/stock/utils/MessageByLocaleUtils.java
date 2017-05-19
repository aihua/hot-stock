package assgn.stock.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Component
public class MessageByLocaleUtils {
	
	@Autowired
	private MessageSource source;

	public String getMessage(String key) {
		return source.getMessage(key, null, Locale.ENGLISH);
	}
}
